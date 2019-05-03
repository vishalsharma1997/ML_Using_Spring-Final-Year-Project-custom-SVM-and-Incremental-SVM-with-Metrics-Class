package com.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.UserDao;
import com.app.entities.User;
import com.app.utilities.MetricsDisplay;
import com.app.utilities.TrainTestUtil;

@Controller
public class RootController {

	@RequestMapping("/forgetPassword")
	public ModelAndView forgetPassword() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forget");
		return modelAndView;
	}

	@RequestMapping("/forgetPassword1")
	public ModelAndView forgetPassword1(@RequestParam("emailid") String emailId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forget");
		if (!emailId.isEmpty()) {
			UserDao userDao = new UserDao();
			User user = userDao.getUser(emailId);
			if (user == null) {
				System.out.println("Invalid Credentials");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("forgetuser", user);
				modelAndView.setViewName("forget1");
			}
		}
		return modelAndView;
	}

	@RequestMapping("/forgetPassword2")
	public ModelAndView forgetPassword2(@RequestParam("phoneno") String phoneNumber, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forget1");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("forgetuser");
		if (user != null) {
			if (!phoneNumber.isEmpty()) {
				if (user.getPhoneNumber().equals(phoneNumber)) {
					session.removeAttribute("forgetuser");
					session.setAttribute("verifiedforgetuser", user);
					modelAndView.setViewName("forget2");
				}
			}
		}
		return modelAndView;
	}

	@RequestMapping("/forgetPassword3")
	public ModelAndView forgetPassword3(@RequestParam("password") String password, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forget1");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("verifiedforgetuser");
		if (user != null) {
			if (!password.isEmpty()) {
				UserDao userDao = new UserDao();
				if (userDao.updateUserPassword(user.getEmailId(), password)) {
					System.out.println("Update Successful !!!");
					session.removeAttribute("verifiedforgetuser");
					modelAndView.setViewName("login");
				}
			}
		}
		return modelAndView;

	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			session.invalidate();
		}
		modelAndView.setViewName("thanks");
		return modelAndView;
	}

	@RequestMapping("/home")
	public ModelAndView home(@RequestParam(value = "emailid", required = false, defaultValue = "") String emailId,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			modelAndView.addObject("username", session.getAttribute("username"));
			modelAndView.setViewName("home");
		} else if (session.getAttribute("username") == null) {

			if (!emailId.isEmpty()) {
				UserDao userDao = new UserDao();
				User user = userDao.getUser(emailId);
				if (user == null || !password.equals(user.getPassword())) {
					System.out.println("Invalid Credentials");
				} else {
					TrainTestUtil trainTestUtil = new TrainTestUtil();
					trainTestUtil.deleteTestTrainDataCache();
					String username = user.getFirstName();
					session.setAttribute("username", username);
					modelAndView.addObject("username", session.getAttribute("username"));
					modelAndView.setViewName("home");
				}
			}
		}
		return modelAndView;
	}

	@RequestMapping("/signup")
	public ModelAndView signUp(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			modelAndView.setViewName("home");
		} else {
			modelAndView.setViewName("signup");
		}
		return modelAndView;
	}

	@RequestMapping("/login")
	public ModelAndView logIn(@RequestParam(value = "emailid", required = false, defaultValue = "") String emailId,
			@RequestParam(value = "firstname", required = false, defaultValue = "") String firstName,
			@RequestParam(value = "lastname", required = false, defaultValue = "") String lastName,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			@RequestParam(value = "phoneno", required = false, defaultValue = "") String phoneNumber,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			modelAndView.setViewName("home");
		} else {
			if (!emailId.isEmpty()) {

				User user = new User(emailId, phoneNumber, firstName, lastName, password);
				UserDao userDao = new UserDao();
				userDao.saveUser(user);
			}
			modelAndView.setViewName("login");
		}

		return modelAndView;
	}

	@RequestMapping("/incrementalSVM")
	public ModelAndView incrementalSVM(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			modelAndView.setViewName("login");
		} else {
			modelAndView.setViewName("incrementalSVM");
		}
		return modelAndView;
	}

	@RequestMapping("/customSVM")
	public ModelAndView customSVM(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			modelAndView.setViewName("login");
		} else {
			modelAndView.setViewName("customSVM");
		}
		return modelAndView;
	}

	@RequestMapping("/incrementalSVMOutput")
	public ModelAndView predictAndDisplayOutput(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			modelAndView.setViewName("login");
		} else {
			TrainTestUtil trainTestUtil = new TrainTestUtil();
			MetricsDisplay predictionOutput = trainTestUtil.displayOutput("Incremental SVM");
			modelAndView.addObject("predictionOutput", predictionOutput);
			modelAndView.setViewName("displayIncrementalSVMOutput");
		}
		return modelAndView;
	}

	@RequestMapping("/customSVMOutput")
	public ModelAndView predictAndDisplaySVMOutput(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			modelAndView.setViewName("login");
		} else {
			TrainTestUtil trainTestUtil = new TrainTestUtil();
			MetricsDisplay predictionOutput = trainTestUtil.displayOutput("Custom SVM");
			modelAndView.addObject("predictionOutput", predictionOutput);
			modelAndView.setViewName("displayCustomSVMOutput");
		}
		return modelAndView;
	}

	@RequestMapping("/incrementalSVMtrain")
	public ModelAndView incrementalSVMTrain(@RequestParam(value ="trainTestRatio", required = false, defaultValue = "") String trainTestRatio, @RequestParam(value ="noOfSubsets", required = false, defaultValue = "") String numberOfSubsets , HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			modelAndView.setViewName("login");
		} else {
			TrainTestUtil trainTestUtil = new TrainTestUtil();
			if(trainTestUtil.setTrainTestRatioForIncrementalVM(trainTestRatio, numberOfSubsets)) {
				trainTestUtil.trainSVM("Incremental SVM");
			}
			modelAndView.setViewName("incrementalSVM");
		}
		return modelAndView;

	}

	@RequestMapping("/customSVMtrain")
	public ModelAndView customSVMTrain(@RequestParam(value ="trainTestRatio", required = false, defaultValue = "") String trainTestRatio, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			modelAndView.setViewName("login");
		} else {
			TrainTestUtil trainTestUtil = new TrainTestUtil();
			if(trainTestUtil.setTrainTestRatioForCustomSVM(trainTestRatio)) {
				trainTestUtil.trainSVM("Custom SVM");
			}
			modelAndView.setViewName("customSVM");
		}
		return modelAndView;

	}

	@RequestMapping("/thanks")
	public ModelAndView thanks(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			modelAndView.setViewName("login");
		} else {
			modelAndView.setViewName("thanks");
		}
		return modelAndView;
	}

}
