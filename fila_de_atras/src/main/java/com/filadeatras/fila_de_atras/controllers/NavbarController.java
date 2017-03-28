package com.filadeatras.fila_de_atras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.filadeatras.fila_de_atras.UserComponent;
import com.filadeatras.fila_de_atras.models.User;
import com.filadeatras.fila_de_atras.repositories.MessageRepository;
import com.filadeatras.fila_de_atras.repositories.UserRepository;

@Controller
public class NavbarController {
	
	@Autowired
	private MessageRepository repository;
	
	@Autowired
	private  UserRepository repositoryUser;
	
	@Autowired
	private UserComponent userComponent;
	
	public NavbarController(){
		
	}
	
	public void loadNavbar(Model model){
		model.addAttribute("loggedUser",userComponent.getLoggedUser());
		if(userComponent.isLoggedUser()){
			User conectedUser = repositoryUser.findOne(userComponent.getLoggedUser().getId());
			model.addAttribute("currentUser", userComponent.getLoggedUser());
			
			int num = repository.findByMessageAddresseeAndMessageNew(conectedUser, true).size();
			model.addAttribute("numEmail", num);
		}
		
		
	}
	
	public void loadProfileNavbar(Model model){
		loadNavbar(model);
		User conectedUser = repositoryUser.findOne(userComponent.getLoggedUser().getId());
		model.addAttribute("isUserAdmin",userComponent.isAdmin());
		model.addAttribute("numFollowers", conectedUser.getUserFollowers().size());
		model.addAttribute("numFollowings", conectedUser.getUserFollowing().size());
	}

}