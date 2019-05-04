import { Moment } from 'moment';

export interface IDrummer {
  id?: number;
  drummerName?: string;
  description?: string;
  email?: string;
  createDate?: Moment;
  modifyDate?: Moment;
  roleId?: number;
  drummerStatisticsId?: number;
}

export const defaultValue: Readonly<IDrummer> = {};
