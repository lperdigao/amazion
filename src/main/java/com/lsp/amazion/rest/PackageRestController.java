package com.lsp.amazion.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lsp.amazion.fixer.model.AmazionPackage;

@RestController
public class PackageRestController {

	@RequestMapping(value = "/package", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getPackage() {
		return new ResponseEntity<>("done", HttpStatus.OK);
	}

	@RequestMapping(value = "/package", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postPackage(@RequestBody AmazionPackage amazionPackage) {
		return new ResponseEntity<>("done", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/package/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> putPackage(@RequestBody AmazionPackage amazionPackage) {
		return new ResponseEntity<>("done", HttpStatus.OK);
	}

	@RequestMapping(value = "/package/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePackage(@PathVariable("id") String id) {
		return new ResponseEntity<>("done", HttpStatus.OK);
	}
}