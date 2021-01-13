package myapp;

	import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

	@RestController
	public class AddController {

		@GetMapping( "/addByTen/{num}")
		public ResponseEntity<Long>  addByTen(@PathVariable Long num) {
			return  new ResponseEntity<>(num+10, HttpStatus.OK);
		}
		
		@GetMapping( "/add/{num1}/{num2}")
		public ResponseEntity<Long>  add(@PathVariable Long num1, @PathVariable Long num2) {
			return  new ResponseEntity<>(num1+num2, HttpStatus.OK);
		}

	}