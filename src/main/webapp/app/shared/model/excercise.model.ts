import { Moment } from 'moment';
import { IExcerciseConfig } from 'app/shared/model/excercise-config.model';
import { IFinishedExcercise } from 'app/shared/model/finished-excercise.model';

export const enum SkillType {
  SPEED = 'SPEED',
  GROOVE = 'GROOVE',
  CREATIVITY = 'CREATIVITY',
  ADAPTABILITY = 'ADAPTABILITY',
  DYNAMICS = 'DYNAMICS',
  INDEPENDENCE = 'INDEPENDENCE',
  LIVE_PERFORMANCE = 'LIVE_PERFORMANCE',
  READING_MUSIC = 'READING_MUSIC'
}

export const enum ExcerciseType {
  RUDIMENT = 'RUDIMENT',
  TECHNIQUE = 'TECHNIQUE',
  SONG = 'SONG',
  PLAYALONG = 'PLAYALONG',
  SIGHT_READING = 'SIGHT_READING'
}

export interface IExcercise {
  id?: number;
  excerciseName?: string;
  description?: string;
  defaultMinutes?: number;
  defaultTargetBpm?: number;
  skillType?: SkillType;
  excerciseType?: ExcerciseType;
  createDate?: Moment;
  modifyDate?: Moment;
  creatorId?: number;
  excerciseConfigs?: IExcerciseConfig[];
  finishedExcercises?: IFinishedExcercise[];
}

export const defaultValue: Readonly<IExcercise> = {};
