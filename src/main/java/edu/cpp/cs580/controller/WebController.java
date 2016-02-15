package edu.cpp.cs580.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.apache.commons.math3.complex.*;
import org.apache.commons.math3.random.*;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;

import edu.cpp.cs580.App;
import edu.cpp.cs580.data.InfoData;
import edu.cpp.cs580.data.User;
import edu.cpp.cs580.data.provider.UserManager;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;	

	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs580/ping
	 */
	@RequestMapping(value = "/cs580/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK";
	}

	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 *
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs580/user/user101
	 */
	@RequestMapping(value = "/cs580/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs580/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs580/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setMajor(major);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs580/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs580/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs580/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}
	/*********** Task3-Hesham **********/
	@RequestMapping(value = "/cs580/HeshamA3T3", method = RequestMethod.GET)
	 	String HeshamT3() {
	 	return "This is Assignment 3, Task 3 implementation by Hesham Darwish.";
	 }
	
	
	/*********** Assignment3 - task3 by Rosa Hyung **********/

	@RequestMapping(value = "/cs580/test", method = RequestMethod.GET)

	String TestForTask3() {
		return "Test for Task 3!!!";
	}
	
	/********Assignment 3 by Chidubem Okam******/
	@RequestMapping(value = "/cs580/Example1", method = RequestMethod.GET)
	String Yes()
	{
		return "Example 1";
		
	}	
	
	/********Assignment 4 by Hesham - Using commonsMath ******/
	
	public static void CommonsMath(String[] args)
	      {
	     	 RandomGenerator randomGenerator = new JDKRandomGenerator();
	     	 System.out.println(randomGenerator.nextInt());
	     	 System.out.println(randomGenerator.nextDouble());
	      
	     	 //Descriptive Statistics like Mean, standard deviation, Max
	     	 DescriptiveStatistics stats = new DescriptiveStatistics();
	     	 stats.addValue(1);
	     	 stats.addValue(2);
	     	 stats.addValue(3);
	     	 stats.addValue(4);
	     	 stats.addValue(5);
	     	 stats.addValue(6);
	     	 stats.addValue(7);
	     	
	     	 System.out.println("Mean is" + stats.getMean()+ "\n");
	     	 System.out.println("Standard Deviation is" +stats.getStandardDeviation()+ "\n");
	     	 System.out.println("Max is"+stats.getMax() +"\n");
	      		      
	     	 Complex c1 = new Complex (1,2);
	     	 Complex c2 = new Complex (2,3);
	     	 System.out.println("Absolute of c1 is " +c1.abs()+ "\n");
	     	 System.out.println("Addition of c1 and c2 is " +(c1.add(c2))+"\n"); 
	     	 
	      }
	      
	
	/********Assignment 4 by Yang******/
	@RequestMapping(value = "/cs580/yang", method = RequestMethod.GET)
	/*need to cope with eliminating <span> tag...*/
	void A4()
	{
        String url = "http://www.yelp.com/la";
        try {
            Document doc = Jsoup.connect(url).get();
            System.out.println(doc.title());
            Elements bizName = doc.select("a.biz-name");
 
            Element thisOne = null;
            for(Iterator it = bizName.iterator(); it.hasNext();)
            {
                thisOne = (Element)it.next();
                System.out.println(thisOne.html());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}	
	
	@RequestMapping(value = "/cs580/yang/1", method = RequestMethod.GET)
	public void getInformationFromWeb(){
		InfoData fromWeb = new InfoData();
        String url = "http://www.tripadvisor.com/Tourism-g32655-Los_Angeles_California-Vacations.html";
        fromWeb.getFromWeb(url);
        fromWeb.printAll();
	}
	
	
}
