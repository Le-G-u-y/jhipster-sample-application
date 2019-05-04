import { Moment } from 'moment';
import { IRole } from 'app/shared/model/role.model';

export interface IPermission {
  id?: number;
  permissionName?: string;
  description?: string;
  createDate?: Moment;
  modifyDate?: Moment;
  roles?: IRole[];
}

export const defaultValue: Readonly<IPermission> = {};
