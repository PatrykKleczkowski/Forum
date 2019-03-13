import {Topic} from "@shared/models/Topic";

export interface Category {
  id: number;
  title: string;
  topics?: Topic[];

}
