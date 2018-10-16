import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
 
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
 
import { Package } from './package';
 
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
 
@Injectable({ providedIn: 'root' })
export class PackageService {
 
  private packagesUrl = '/package';  // URL to web api
 
  constructor(
    private http: HttpClient) { }
 
  /** GET packages from the server */
  getPackages (): Observable<Package[]> {
    return this.http.get<Package[]>(this.packagesUrl)
      .pipe(
        tap(packages => this.log('fetched packages')),
        catchError(this.handleError('getpackages', []))
      );
  }
 
  /** GET package by id. Return `undefined` when id not found */
  getPackageNo404<Data>(id: number): Observable<Package> {
    const url = `${this.packagesUrl}/?id=${id}`;
    return this.http.get<Package[]>(url)
      .pipe(
        map(packages => packages[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? `fetched` : `did not find`;
          this.log(`${outcome} package id=${id}`);
        }),
        catchError(this.handleError<Package>(`getpackage id=${id}`))
      );
  }
 
  /** GET package by id. Will 404 if id not found */
  getPackage(id: number): Observable<Package> {
    const url = `${this.packagesUrl}/${id}`;
    return this.http.get<Package>(url).pipe(
      tap(_ => this.log(`fetched package id=${id}`)),
      catchError(this.handleError<Package>(`getpackage id=${id}`))
    );
  }
 
  /* GET packages whose name contains search term */
  searchPackages(term: string): Observable<Package[]> {
    if (!term.trim()) {
      // if not search term, return empty package array.
      return of([]);
    }
    return this.http.get<Package[]>(`${this.packagesUrl}/?name=${term}`).pipe(
      tap(_ => this.log(`found packages matching "${term}"`)),
      catchError(this.handleError<Package[]>('searchpackages', []))
    );
  }
 
  //////// Save methods //////////
 
  /** POST: add a new package to the server */
  addPackage (pkg: Package): Observable<Package> {
    return this.http.post<Package>(this.packagesUrl, pkg, httpOptions).pipe(
      tap((pkg: Package) => this.log(`added package w/ id=${pkg.id}`)),
      catchError(this.handleError<Package>('addpackage'))
    );
  }
 
  /** DELETE: delete the package from the server */
  deletePackage (pkg: Package | number): Observable<Package> {
    const id = typeof pkg === 'number' ? pkg : pkg.id;
    const url = `${this.packagesUrl}/${id}`;
 
    return this.http.delete<Package>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted package id=${id}`)),
      catchError(this.handleError<Package>('deletepackage'))
    );
  }
 
  /** PUT: update the package on the server */
  updatePackage (pkg: Package): Observable<any> {
    return this.http.put(this.packagesUrl, pkg, httpOptions).pipe(
      tap(_ => this.log(`updated package id=${pkg.id}`)),
      catchError(this.handleError<any>('updatepackage'))
    );
  }
 
  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
 
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
 
      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);
 
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
 
  /** Log a packageService message with the MessageService */
  private log(message: string) {
    console.log(`packageService: ${message}`);
  }
}