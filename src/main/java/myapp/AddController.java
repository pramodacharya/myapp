package myapp;

import org.springframework.web.bind.annotation.RestController;
import myapp.repository.CacheRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class AddController {

    private final CacheRepository cacheRepository;

    @Autowired
    public AddController(CacheRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

	@GetMapping("/addByTen/{num}")
	public ResponseEntity<Long> addByTen(@PathVariable Long num) {
		return new ResponseEntity<>(num + 10, HttpStatus.OK);
	}

	@GetMapping("/add/{num1}/{num2}")
	public ResponseEntity<Long> add(@PathVariable Long num1, @PathVariable Long num2) {
		return new ResponseEntity<>(num1 + num2, HttpStatus.OK);
	}

	@GetMapping("/checkPrime/{num}")
	public ResponseEntity<Boolean> add(@PathVariable Long num) {
		Optional<Boolean> s = cacheRepository.get(num.toString());
        if (s.isPresent()) {
        	System.out.println(num + " is found in redis cache");
        	return new ResponseEntity<>(s.get(), HttpStatus.OK);
        }
        System.out.println(num + " is not found in redis cache. Evalating it's primeness and chaching.....");
        boolean isPrime = isPrime(num);
        cacheRepository.put(num.toString(), isPrime);
		return new ResponseEntity<>(isPrime, HttpStatus.OK);
	}

	public boolean isPrime(long num) {
		long mid = num / 2;
		boolean isPrime = true;
		if (num == 1)
			isPrime = false;
		else if (num == 2)
			isPrime = true;
		else if (num % 2 == 0)
			isPrime = false;
		else
			for (int i = 2; i <= mid; i++) {
				if(num%i==0) isPrime =false;
			}
		return isPrime;
	}
}