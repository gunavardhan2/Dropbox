package controller;

import model.LoginRequest;
import model.SignUpRequest;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtTokenProvider;
import service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthController(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userService.getUserByUsername(signUpRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body(new ErrorResponse() {
                @Override
                public HttpStatusCode getStatusCode() {
                    return null;
                }

                @Override
                public ProblemDetail getBody() {
                    return null;
                }
            });
        }

        User newUser = new User();
        newUser.setUsername(signUpRequest.getUsername());
        newUser.setPassword(signUpRequest.getPassword());

        userService.addUser(newUser);

        String s = "User registered successfully";
        return ResponseEntity.ok(s);
    }

    @PostMapping("/signin")
    public <Authentication> ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        Authentication authentication = null;
        jwtTokenProvider.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        String token = jwtTokenProvider.generateToken((Authentication) null);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}

