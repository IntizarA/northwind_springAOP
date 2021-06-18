package kodlamaio.northwind.api.controllers;


import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping(value="/api/users")
public class UsersController {
	
	private UserService userService;
@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	@PostMapping(value="/add")
	public ResponseEntity<?> add(@Valid @RequestBody User user) {//neticeni Result olaraq vermesini isteyirik amma netice success olmaya da biler ona gore Result evezine ? qaytar deyirik. Yeni veziyyete gore ne lazimdirsa neticede onu qaytar
		return ResponseEntity.ok(this.userService.add(user));
	}
@ExceptionHandler(MethodArgumentNotValidException.class)//global choxlu try catch yazmagin evezine
@ResponseStatus(HttpStatus.BAD_REQUEST)// 500 xetasi
	public ErrorDataResult<Object> handleValidationException	(MethodArgumentNotValidException exceptions){
	Map<String,String> validationErrors=new HashMap<String,String>();//dictionay key ve xeta
	                           
	for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {// xetalar burada toplanir ve 41-ci setirdeki dictionary-e elave edilir
		validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());// yaranan butun xetalari oxu
	}
	ErrorDataResult<Object> errors =new ErrorDataResult<Object>(validationErrors,"Doğrulama xətaları");
	return errors;
	}

}
