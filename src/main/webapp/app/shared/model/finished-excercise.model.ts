import { Moment } from 'moment';

export interface IFinishedExcercise {
  id?: number;
  actualBpm?: number;
  actualMinutes?: number;
  createDate?: Moment;
  modifyDate?: Moment;
  excerciseId?: number;
  finishedSessionId?: number;
}

export const defaultValue: Readonly<IFinishedExcercise> = {};
