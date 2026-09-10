package com.yosoymikael;

import com.yosoymikael.model.Journey;
import com.yosoymikael.model.JourneyReservation;
import com.yosoymikael.repository.JourneyReservationRepository;
import com.yosoymikael.service.JourneyReservationService;
import com.yosoymikael.service.JourneyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class YoSoyBackendApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JourneyService journeyService;

	@Autowired
	private JourneyReservationService journeyReservationService;

	@Autowired
	private JourneyReservationRepository journeyReservationRepository;

	@Test
	void contextLoads() {
		assertNotNull(dataSource, "DataSource no debe ser nulo");
	}

	@Test
	void testPostgreSqlConnection() throws SQLException {
		assertNotNull(dataSource, "DataSource no debe ser nulo");
		try (Connection connection = dataSource.getConnection()) {
			assertTrue(connection.isValid(2), "La conexion a la base de datos debe ser valida");
			DatabaseMetaData metaData = connection.getMetaData();
			assertEquals("PostgreSQL", metaData.getDatabaseProductName());
			assertEquals("yosoymikael", connection.getCatalog());
		}
	}

	@Test
	void testPureHibernateHelpersAndCascade() {
		// 1. Crear viaje padre
		Journey journey = Journey.builder()
				.journeyName("Viaje de Prueba Hibernate")
				.journeyDescription("Verificando cascade, orphan removal y dirty checking")
				.journeyDate(LocalDate.now().plusMonths(1))
				.journeyPrice(new BigDecimal("1500000.00"))
				.build();
		Journey savedJourney = journeyService.createJourney(journey);
		assertNotNull(savedJourney.getJourneyId());

		// 2. Crear reserva usando SOLO el helper (addReservation) sin .save() en el repositorio hijo
		JourneyReservation reservation = JourneyReservation.builder()
				.travelersFullName("Viajero Test")
				.travelersPhone("3001112233")
				.travelersMail("test@viajero.com")
				.travelersAddress("Calle Test 123")
				.build();
		
		JourneyReservation created = journeyReservationService.createReservation(savedJourney.getJourneyId(), reservation);
		assertNotNull(created.getJourneyReservationId(), "CascadeType.ALL debio generar el ID e insertar en la BD");

		// 3. Verificar en BD que la reserva existe
		List<JourneyReservation> list = journeyReservationRepository.findByJourney_JourneyId(savedJourney.getJourneyId());
		assertFalse(list.isEmpty());
		assertEquals("Viajero Test", list.get(0).getTravelersFullName());

		// 4. Actualizar mediante Dirty Checking (sin .save())
		JourneyReservation updateDetails = JourneyReservation.builder()
				.travelersFullName("Viajero Test Actualizado")
				.travelersPhone("3009998877")
				.travelersMail("actualizado@viajero.com")
				.travelersAddress("Nueva Direccion 456")
				.build();
		
		journeyReservationService.updateReservation(created.getJourneyReservationId(), updateDetails);
		JourneyReservation updatedFromDb = journeyReservationService.getReservationById(created.getJourneyReservationId());
		assertEquals("Viajero Test Actualizado", updatedFromDb.getTravelersFullName(), "Dirty checking debio actualizar en la BD");

		// 5. Eliminar mediante Orphan Removal (removeReservation sin .delete())
		journeyReservationService.deleteReservation(created.getJourneyReservationId());
		List<JourneyReservation> listAfterDelete = journeyReservationRepository.findByJourney_JourneyId(savedJourney.getJourneyId());
		assertTrue(listAfterDelete.isEmpty(), "orphanRemoval = true debio eliminar la reserva de la BD");
	}

}
