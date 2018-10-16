import { Product } from './product';
export class Package {
  id: string;
  name: string;
  description: string;
  products:Product[];
  price: number;
}