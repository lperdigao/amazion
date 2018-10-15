package com.lsp.amazion.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lsp.amazion.fixer.model.AmazionPackage;
import com.lsp.amazion.services.api.ConvertionService;
import com.lsp.amazion.services.api.PackageManagementService;

@RestController
public class PackageRestController {

	private PackageManagementService packageManagementService;
	private ConvertionService convertionService;

	@Autowired
	PackageRestController(PackageManagementService packageManagementService, ConvertionService convertionService) {
		this.packageManagementService = packageManagementService;
		this.convertionService = convertionService;
	}

	@RequestMapping(value = "/package", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AmazionPackage>> getPackage(
			@RequestParam(value = "currency", required = false) String currency) throws IOException {
		List<AmazionPackage> pakgs = packageManagementService.listPackages();
		if (currency != null) {
			double conv = this.convertionService.convert(1, "USD", currency);
			pakgs.forEach(pak -> pak.setPrice((int) (pak.getPrice() * conv)));
		}
		return new ResponseEntity<>(pakgs, HttpStatus.OK);
	}

	@RequestMapping(value = "/package/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AmazionPackage> getSinglePackage(@PathVariable("id") String id,
			@RequestParam(value = "currency", required = false) String currency) throws IOException {
		AmazionPackage pakg = packageManagementService.findPackage(id);
		if (currency != null) {
			double conv = this.convertionService.convert(1, "USD", currency);
			pakg.setPrice((int) (pakg.getPrice() * conv));
		}
		return new ResponseEntity<>(pakg, HttpStatus.OK);
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