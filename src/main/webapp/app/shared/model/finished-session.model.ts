import { Moment } from 'moment';
import { IFinishedExcercise } from 'app/shared/model/finished-excercise.model';

export interface IFinishedSession {
  id?: number;
  minutesTotal?: number;
  note?: string;
  createDate?: Moment;
  modifyDate?: Moment;
  planId?: number;
  finishedExcercises?: IFinishedExcercise[];
}

export const defaultValue: Readonly<IFinishedSession> = {};
