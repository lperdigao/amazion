package com.lsp.amazion.services.api;

import java.util.List;

import com.lsp.amazion.fixer.model.AmazionPackage;

public interface PackageManagementService {

	/**
	 * Lists all packages
	 * 
	 * @return a list of packages
	 */
	List<AmazionPackage> listPackages();

	/**
	 * Find a package
	 * 
	 * @param id
	 *            id of the package
	 * @return returns the package or null
	 */
	AmazionPackage findPackage(String id);

	/**
	 * Saves a
	 * 
	 * @param amazionPackage
	 *            package to be saved
	 * @return the saved packaged
	 */
	AmazionPackage savePackage(AmazionPackage amazionPackage);

	/**
	 * Updates a amazionPackage
	 * 
	 * @param amazionPackage
	 *            the updated package
	 * @return
	 */
	AmazionPackage updatePackage(AmazionPackage amazionPackage);

	/**
	 * Deletes a package
	 * 
	 * @param id
	 *            id of the package to delete
	 * @return the deleted package
	 */
	AmazionPackage deletePackage(String id);
}
