import { Moment } from 'moment';

export interface IExcerciseConfig {
  id?: number;
  actualBpm?: number;
  targetBpm?: number;
  minutes?: number;
  note?: string;
  createDate?: Moment;
  modifyDate?: Moment;
  excerciseId?: number;
  planId?: number;
}

export const defaultValue: Readonly<IExcerciseConfig> = {};
