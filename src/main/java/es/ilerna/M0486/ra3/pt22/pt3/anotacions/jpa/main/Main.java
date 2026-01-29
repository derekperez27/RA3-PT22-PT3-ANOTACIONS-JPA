package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Car;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Motorcycle;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Person;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Plane;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Student;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Teacher;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Vehicle;

import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main.HibernateSession;

public class Main {

	private static List<Person> people = new ArrayList<>();
	private static List<Vehicle> vehicles = new ArrayList<>();
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int opcio;

		do {
			System.out.println("\n===== MENÚ PRINCIPAL =====");
			System.out.println("1) Fase 1: Crear dades de prova");
			System.out.println("2) Fase 2: Treure vehicles de persones");
			System.out.println("3) Fase 3: Actualitzar un vehicle");
			System.out.println("0) Sortir");
			System.out.print("Escull una opció: ");

			opcio = sc.nextInt();
			sc.nextLine();

			switch (opcio) {
				case 1:
					fase1();
					break;
				case 2:
					fase2();
					break;
				case 3:
					fase3();
					break;
				case 0:
					System.out.println("Fins aviat!");
					break;
				default:
					System.out.println("Opció incorrecta.");
			}

		} while (opcio != 0);

		sc.close();
		HibernateSession.getSessionFactory().close();
	}

	private static void fase1() {
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			// Persons (persist in order)
			Student anna = new Student();
			anna.setName("Anna");
			anna.setSurname("Lopez");
			anna.setPhoneNumber(111111111);
			anna.setStudentCode("STU001");
			session.persist(anna);
			people.add(anna);		

			Student jordi = new Student();
			jordi.setName("Jordi");
			jordi.setSurname("Martinez");
			jordi.setPhoneNumber(222222222);
			jordi.setStudentCode("STU002");
			session.persist(jordi);
			people.add(jordi);

			Student clara = new Student();
			clara.setName("Clara");
			clara.setSurname("Sanchez");
			clara.setPhoneNumber(333333333);
			clara.setStudentCode("STU003");
			session.persist(clara);
			people.add(clara);

			Teacher joan = new Teacher();
			joan.setName("Joan");
			joan.setSurname("Perez");
			joan.setPhoneNumber(444444444);
			joan.setTeacherCode("TEA001");
			session.persist(joan);
			people.add(joan);

			Teacher maria = new Teacher();
			maria.setName("Maria");
			maria.setSurname("Gomez");
			maria.setPhoneNumber(555555555);
			maria.setTeacherCode("TEA002");
			session.persist(maria);
			people.add(maria);

			Teacher pere = new Teacher();
			pere.setName("Pere");
			pere.setSurname("Ruiz");
			pere.setPhoneNumber(666666666);
			pere.setTeacherCode("TEA003");
			session.persist(pere);
			people.add(pere);

			Car toyota = new Car();
			toyota.setBrand("Toyota");
			toyota.setPrice(18000f);
			toyota.setYear(2020);
			toyota.setDoors(5);
			toyota.setSeats(5);
			toyota.setPerson(people.get(0)); 
			people.get(0).addVehicle(toyota);
			session.persist(toyota);
			vehicles.add(toyota);

			Car ford = new Car();
			ford.setBrand("Ford");
			ford.setPrice(15000f);
			ford.setYear(2019);
			ford.setDoors(3);
			ford.setSeats(4);
			ford.setPerson(people.get(4)); 
			people.get(4).addVehicle(ford);
			session.persist(ford);
			vehicles.add(ford);

			Plane cessna = new Plane();
			cessna.setBrand("Cessna");
			cessna.setPrice(120000f);
			cessna.setYear(2015);
			cessna.setTailNumber(11111);
			cessna.setAutopilot(true);
			cessna.setPerson(people.get(3)); 
			people.get(3).addVehicle(cessna);
			session.persist(cessna);
			vehicles.add(cessna);

			Plane boeing = new Plane();
			boeing.setBrand("Boeing");
			boeing.setPrice(900000f);
			boeing.setYear(2010);
			boeing.setTailNumber(22222);
			boeing.setAutopilot(false);
			boeing.setPerson(people.get(2)); 
			people.get(2).addVehicle(boeing);
			session.persist(boeing);
			vehicles.add(boeing);

			Motorcycle yamaha = new Motorcycle();
			yamaha.setBrand("Yamaha");
			yamaha.setPrice(9000f);
			yamaha.setYear(2021);
			yamaha.setHasSidecar(true);
			yamaha.setPerson(people.get(1)); 
			people.get(1).addVehicle(yamaha);
			session.persist(yamaha);
			vehicles.add(yamaha);

			Motorcycle harley = new Motorcycle();
			harley.setBrand("Harley-Davidson");
			harley.setPrice(20000f);
			harley.setYear(2018);
			harley.setHasSidecar(false);
			harley.setPerson(people.get(5)); 
			people.get(5).addVehicle(harley);
			session.persist(harley);
			vehicles.add(harley);

			tx.commit();
			System.out.println("Fase 1: dades persistides correctament.");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Error a fase1: " + e.getMessage());
		} finally {
			session.close();
		}

	}
	private static void fase2() {
	    Session session = HibernateSession.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try {
	        Vehicle v = session.get(Vehicle.class, 1);
	        if (v == null) {
	            System.out.println("Fase 2: Vehicle id=1 no trobat.");
	            tx.rollback();
	            return;
	        }

	        Person owner = v.getPerson();
	        if (owner == null) {
	            System.out.println("Fase 2: El vehicle ja no té propietari.");
	        } else {
	            if (owner.getId() != null && owner.getId().equals(1)) {
	      
	                v.setPerson(null);
	                session.merge(v);
	                session.flush();
	        
	                session.refresh(owner);
	                System.out.println("Fase 2: desvinculació executada correctament.");
	            } else {
	                v.setPerson(null);
	                session.merge(v);
	                session.flush();
	                session.refresh(owner);
	                System.out.println("Fase 2: desvinculació executada (owner diferent de 1).");
	            }
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        System.err.println("Error a fase2: " + e.getMessage());
	    } finally {
	        session.close();
	    }
	}

	private static void fase3() {
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Vehicle v = session.get(Vehicle.class, 1);
			if (v == null) {
				System.out.println("Fase 3: Vehicle id=1 no trobat.");
				if (tx != null) tx.rollback();
				return;
			}

			Scanner sc = new Scanner(System.in);
			System.out.println("Fase 3: introduir nous valors per al vehicle id=1 (deixa buit per no canviar)");

			System.out.print("Marca (actual: " + v.getBrand() + "): ");
			String brand = sc.nextLine().trim();
			if (!brand.isEmpty()) v.setBrand(brand);

			System.out.print("Preu (actual: " + v.getPrice() + "): ");
			String priceStr = sc.nextLine().trim();
			if (!priceStr.isEmpty()) {
				try { v.setPrice(Float.parseFloat(priceStr)); } catch (NumberFormatException e) {}
			}

			System.out.print("Any (actual: " + v.getYear() + "): ");
			String yearStr = sc.nextLine().trim();
			if (!yearStr.isEmpty()) {
				try { v.setYear(Integer.parseInt(yearStr)); } catch (NumberFormatException e) {}
			}

			if (v instanceof es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Car) {
				es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Car c = (es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Car) v;
				System.out.print("Portes (actual: " + c.getDoors() + "): ");
				String d = sc.nextLine().trim();
				if (!d.isEmpty()) { try { c.setDoors(Integer.parseInt(d)); } catch (NumberFormatException e) {} }
				System.out.print("Places (actual: " + c.getSeats() + "): ");
				String s = sc.nextLine().trim();
				if (!s.isEmpty()) { try { c.setSeats(Integer.parseInt(s)); } catch (NumberFormatException e) {} }
			}

			if (v instanceof es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Plane) {
				es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Plane p = (es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Plane) v;
				System.out.print("TailNumber (actual: " + p.getTailNumber() + "): ");
				String t = sc.nextLine().trim();
				if (!t.isEmpty()) { try { p.setTailNumber(Integer.parseInt(t)); } catch (NumberFormatException e) {} }
				System.out.print("Autopilot (actual: " + p.getAutopilot() + ") [true/false]: ");
				String a = sc.nextLine().trim();
				if (!a.isEmpty()) { p.setAutopilot(Boolean.parseBoolean(a)); }
			}

			if (v instanceof es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Motorcycle) {
				es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Motorcycle m = (es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Motorcycle) v;
				System.out.print("Sidecar (actual: " + m.getHasSidecar() + ") [true/false]: ");
				String scStr = sc.nextLine().trim();
				if (!scStr.isEmpty()) { m.setHasSidecar(Boolean.parseBoolean(scStr)); }
			}

			session.merge(v);
			tx.commit();
			System.out.println("Fase 3: vehicle id=1 actualitzat correctament.");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Error a fase3: " + e.getMessage());
		} finally {
			session.close();
		}
	}
}
