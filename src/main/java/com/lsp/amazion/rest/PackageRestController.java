package com.lsp.amazion.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lsp.amazion.fixer.model.AmazionPackage;
import com.lsp.amazion.services.api.PackageManagementService;

@RestController
public class PackageRestController {

	private PackageManagementService packageManagementService;

	@Autowired
	PackageRestController(PackageManagementService packageManagementService) {
		this.packageManagementService = packageManagementService;
	}

	@RequestMapping(value = "/package", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AmazionPackage>> getPackage() {
		return new ResponseEntity<>(packageManagementService.listPackages(), HttpStatus.OK);
	}

	@RequestMapping(value = "/package", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AmazionPackage> postPackage(@RequestBody AmazionPackage amazionPackage) {
		return new ResponseEntity<>(packageManagementService.savePackage(amazionPackage), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/package/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AmazionPackage> putPackage(@RequestBody AmazionPackage amazionPackage) {
		return new ResponseEntity<>(packageManagementService.updatePackage(amazionPackage), HttpStatus.OK);
	}

	@RequestMapping(value = "/package/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AmazionPackage> deletePackage(@PathVariable("id") String id) {
		return new ResponseEntity<>(packageManagementService.deletePackage(id), HttpStatus.OK);
	}
}