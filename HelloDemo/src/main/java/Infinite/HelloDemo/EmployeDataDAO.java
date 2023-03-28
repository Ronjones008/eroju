package Infinite.HelloDemo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;



@ManagedBean
@SessionScoped
public class EmployeDataDAO {
	
	public int empId;
	public EmployeData employeData;
	
	SessionFactory sessionFactory;

	List<EmployeData> empList = new ArrayList<>();


	public List<EmployeData> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmployeData> empList) {
		this.empList = empList;
	}





	public String addEmploye(EmployeData e) {
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.save(e);
		t.commit();
		return "showEmploye";
	}

	public List<EmployeData> showEdata(){
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(EmployeData.class);
		List<EmployeData> eList = cr.list();    
		return eList;
	}


	public List<EmployeData> searchEmploy(int empId)  {
		sessionFactory = SessionHelper.getConnection();
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(EmployeData.class);
		cr.add(Restrictions.eq("empId", empId));

		List<EmployeData> eList = cr.list();
		EmployeData employ =null;


		if (eList.size() >0) {
			employ = eList.get(0);
		}
	    setEmpList(eList);

	    if (employ == null) {
	        FacesMessage message = new FacesMessage("No Record Found For This EmployId");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }

	    return null;
	}
	
	public void loadEmployeData() {
		employeData = searchEmp(empId);
	}

       public EmployeData searchEmp(int empId) {
    	   sessionFactory = SessionHelper.getConnection();
   		Session session = sessionFactory.openSession();
   		Criteria cr = session.createCriteria(EmployeData.class);
   		cr.add(Restrictions.eq("empId", empId));

   		List<EmployeData> eList = cr.list();
   		if(eList.size()==0) {
   			return null;
   		}
   		return eList.get(0);
       }

		public String updateEmploy(){
			sessionFactory = SessionHelper.getConnection();
			Session session = sessionFactory.openSession();
			
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(employeData);
			transaction.commit();
			return "Student Details Updated";
		}


		//	public List<EmployeData> searchEmploy(int empId) throws IOException {
		//	    sessionFactory = SessionHelper.getConnection();
		//	    Session session = sessionFactory.openSession();
		//	    Criteria cr = session.createCriteria(EmployeData.class);
		//	    cr.add(Restrictions.eq("empId", empId));
		//
		//	    List<EmployeData> eList = cr.list();
		//	    EmployeData employ = null;
		//
		//	    if (eList.size() > 0) {
		//	        employ = eList.get(0);
		//	    }
		//
		//	    setEmpList(eList);
		//	    setEmploy(employ); // Assuming there is a method to set the selected employee object
		//
		//	    if (employ == null) {
		//	        FacesMessage message = new FacesMessage("No record found");
		//	        FacesContext.getCurrentInstance().addMessage(null, message);
		//	    }
		//
		//	    return getEmpList();
		//	}





	}



