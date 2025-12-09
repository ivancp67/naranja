package ceu.dam.ad.users.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.ad.users.controller.UserService;
import ceu.dam.ad.users.controller.UserServiceImpl;
import ceu.dam.ad.users.dto.request.LoginRequestDto;
import ceu.dam.ad.users.dto.request.UpdatePasswordResquestDto;
import ceu.dam.ad.users.dto.request.UserResquestDto;
import ceu.dam.ad.users.dto.response.LoginResponseDto;
import ceu.dam.ad.users.dto.response.NewUserResponseDto;
import ceu.dam.ad.users.dto.response.UserResponseDto;
import ceu.dam.ad.users.exception.DuplicateUserException;
import ceu.dam.ad.users.exception.UserException;
import ceu.dam.ad.users.exception.UserNotFoundException;
import ceu.dam.ad.users.exception.UserUnauthorizedException;
import ceu.dam.ad.users.model.User;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;

	@PostMapping()
	public NewUserResponseDto createUser(@Valid @RequestBody UserResquestDto userDto) throws DuplicateUserException, UserException {
		User userEntity = new ModelMapper().map(userDto, User.class);
		service.createUser(userEntity);
		return new ModelMapper().map(userEntity, NewUserResponseDto.class);
	}
	
	@PutMapping("/{id}/password")
	public void updateUser(@PathVariable Long id, @Valid @RequestBody UpdatePasswordResquestDto userDto) throws UserNotFoundException, UserUnauthorizedException, UserException {
		service.changePassword(id, userDto.getOldPassword(), userDto.getNewPassword());
	}
	
	@PostMapping("/login")
	@Operation(summary = "Login de usuario", description = "Permite a un usuario hacer login con su cuenta")
	public LoginResponseDto login(@RequestBody @Valid LoginRequestDto userDto) throws UserNotFoundException, UserUnauthorizedException, UserException {
		User user = service.login(userDto.getLogin(), userDto.getPassword());
		return new ModelMapper().map(user, LoginResponseDto.class);
	}
	
	@GetMapping("/{id}")
	public UserResponseDto getUser(@PathVariable Long id) throws UserNotFoundException, UserException{
		User user = service.getUser(id);
		return new ModelMapper().map(user, UserResponseDto.class);
	}
}
