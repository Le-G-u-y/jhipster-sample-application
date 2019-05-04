import { Moment } from 'moment';
import { IExcerciseConfig } from 'app/shared/model/excercise-config.model';

export interface IPlan {
  id?: number;
  planName?: string;
  planFocus?: string;
  description?: string;
  minutesPerSession?: number;
  sessionsPerWeek?: number;
  targetDate?: Moment;
  createDate?: Moment;
  modifyDate?: Moment;
  excerciseConfigs?: IExcerciseConfig[];
  creatorId?: number;
  ownerId?: number;
  finishedSessionId?: number;
}

export const defaultValue: Readonly<IPlan> = {};
