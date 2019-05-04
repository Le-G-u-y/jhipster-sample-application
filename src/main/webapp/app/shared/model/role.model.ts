import { Moment } from 'moment';
import { IPermission } from 'app/shared/model/permission.model';
import { IDrummer } from 'app/shared/model/drummer.model';

export interface IRole {
  id?: number;
  roleName?: string;
  description?: string;
  createDate?: Moment;
  modifyDate?: Moment;
  permissions?: IPermission[];
  drummers?: IDrummer[];
}

export const defaultValue: Readonly<IRole> = {};
