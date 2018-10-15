package com.lsp.amazion.services.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lsp.amazion.fixer.model.AmazionPackage;
import com.lsp.amazion.services.api.PackageManagementService;

@Repository
public class PackageManagementServiceImp implements PackageManagementService {

	private Map<String, AmazionPackage> packages;

	PackageManagementServiceImp() {
		packages = new HashMap<>();
	}

	@Override
	public AmazionPackage findPackage(String id) {
		return packages.get(id);
	}

	@Override
	public AmazionPackage savePackage(AmazionPackage amazionPackage) {
		packages.put(amazionPackage.getId(), amazionPackage);
		return amazionPackage;
	}

	@Override
	public AmazionPackage updatePackage(AmazionPackage amazionPackage) {
		AmazionPackage packg = packages.get(amazionPackage.getId());
		if (packg == null) {
			// no packg found
			return null;
		}
		packages.put(amazionPackage.getId(), amazionPackage);
		return amazionPackage;
	}

	@Override
	public AmazionPackage deletePackage(String id) {
		return packages.remove(id);
	}

	@Override
	public List<AmazionPackage> listPackages() {
		return new ArrayList(packages.values());
	}

}
