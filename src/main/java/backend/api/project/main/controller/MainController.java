package backend.api.project.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainController {
	
	/**
	 * main data
	 * @return
	 */
	@GetMapping("/main")
	public ResponseEntity<?> mainData() {
		Map<String, Object> resultMap = new HashMap();
		
		return ResponseEntity.ok(resultMap);
	}

}
