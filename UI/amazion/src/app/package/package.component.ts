import { Component, OnInit } from '@angular/core';
import { Package } from '../package';
import { PackageService } from '../package.service';
@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {
	
	packagesList: Package[] = [];
	
	
	amazionPackage: Package = {
		id: "package1",
		name: "package1",
		description: "this package",
		products : [{id:"ada", name :"prod1", usdprice:200}],
		price: 0
	};
	
	
	constructor(private packageService: PackageService) {	}
	
	getPackages(): void {
		this.packageService.getPackages()
      .subscribe(packgs => this.packagesList = packgs);
	}

  ngOnInit() {  
	//this.packagesList.push(this.amazionPackage);
	this.getPackages();
	console.log(this.packagesList)
  }
}
