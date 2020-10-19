package com.example.Employee;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.WeekFields;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee.Exception.ResourceNotFoundException;
import com.example.Employee.Model.Employee;
import com.example.Employee.Model.Users;
//import com.employee.Repository.EmployeeRepo;
import com.example.Employee.Repo.EmployeeRepo;
import com.example.Employee.Repo.UsersRepo;


@CrossOrigin("*")//origins = "http://localhost:4500")
@RestController
@RequestMapping("/Emp")
public class EmployeeController {
	@Autowired
	EmployeeRepo employeeRepository;
	@PersistenceContext
	private EntityManager em;

	@Autowired
	UsersRepo usrpo;

	@GetMapping(path = "/basicauth")
	public AuthenticationBean authBean() {
		return new AuthenticationBean("You are authenticated");
	}
	@PostMapping("/authenticate")
public ResponseEntity<String> validateLogin(Users user) {
		String decodedString="";
			Users usr=usrpo.findByUserName(user.getUserName());
			if(usr!=null){
	String passwrd=usr.getPassword();
	byte[] decodedBytes = java.util.Base64.getDecoder().decode(passwrd);
	 decodedString = new String(decodedBytes);

			}
	if(decodedString.equals(user.getPassword()))
	{
		//return "sucess";
		return ResponseEntity.ok().body("success");

	}
	else
	{
		//return "failure";
		return ResponseEntity.ok().body("failure");


	}
	}

	@GetMapping("/employees")
	public List<com.example.Employee.Model.Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employeessave")
	public Employee createEmployee( @RequestBody Employee employee) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());

		employee.setJoindate(date);
		return employeeRepository.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			 @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setActive(employeeDetails.getActive());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	

	@PostMapping("/employeesanniv")
	public  Map<String, Object> getAnniversary( @RequestBody Employee employee)
			throws ResourceNotFoundException, ParseException {
		int dte=0;
		String currdate=employee.getFirstName();
	//Date curr=new Date(currdate);
		LocalDate localDate 
        = LocalDate.of(2020, 
                       Month.OCTOBER, 18); 
		LocalDate Startdte 
        = LocalDate.of(1947, 
                       Month.AUGUST, 15); 
		LocalDate endte 
        = LocalDate.of(1947, 
                       Month.AUGUST, 15); 

    // Find the day from the local date 
    DayOfWeek dayOfWeek 
        = DayOfWeek.from(localDate); 

    // Printing the day of the week 
    // and its Int value 
    System.out.println("Day of the Week on"
                       + " 15th August 1947 - "
                       + dayOfWeek.name()); 

    int val = dayOfWeek.getValue(); 

    System.out.println("Int Value of "
                       + dayOfWeek.name() 
                       + " - " + val);
    List<String> myList = new ArrayList<String> ();
    LocalDate zonedlt 
    = LocalDate.parse("2020-10-18"); 

   // LocalDate Startdte,endte; 
  //  = zonedlt.minus(Period.ofDays(30)); 


if(val==1)
{
		Date Startdate=new Date(currdate) ;
		// endte  =((LocalDate) curr).plusDays(6);
				//zonedlt.minus(Period.ofDays(30)); 


}
if(val==2)
{
	 Startdte= zonedlt.minus(Period.ofDays(1));
	 endte  =(zonedlt).plusDays(5);
	
}
if(val==3)
{
	 Startdte= zonedlt.minus(Period.ofDays(2));
	 endte  =(zonedlt).plusDays(4);
	
}
if(val==4)
{
	 Startdte= zonedlt.minus(Period.ofDays(3));
	 endte  =(zonedlt).plusDays(3);
	
}
if(val==5)
{
	 Startdte= zonedlt.minus(Period.ofDays(4));
	 endte  =(zonedlt).plusDays(2);
	
}
if(val==6)
{
	 Startdte= zonedlt.minus(Period.ofDays(5));
	 endte  =(zonedlt).plusDays(1);
	
}
if(val==7)
{
	 Startdte= zonedlt.minus(Period.ofDays(6));
	 endte  =(zonedlt);
	
}
String datestr = ((Startdte).toString());
String datesend = ((endte).toString());
java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd").parse(datestr);
java.util.Date temp1 = new SimpleDateFormat("yyyy-MM-dd").parse(datesend);

System.out.println(temp);

	      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	      CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
	      Root<Employee> emp = query.from(Employee.class);
	      query.select(emp)
	           .where(criteriaBuilder.between(emp.get("joindate"), temp, temp1));
	      List<Employee> resultLists = em.createQuery(query).getResultList();
	  	Map <String,Object> dataHashMap=new HashMap<String,Object>();
	  	dataHashMap.put("annivlist", resultLists);
		return dataHashMap;
	}
	private static Timestamp localToTimeStamp(LocalDate date){
	      return Timestamp.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
	  }

	
	
}
