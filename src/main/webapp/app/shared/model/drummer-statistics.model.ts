import { Moment } from 'moment';

export interface IDrummerStatistics {
  id?: number;
  selfAssessedLevelSpeed?: number;
  selfAssessedLevelGroove?: number;
  selfAssessedLevelCreativity?: number;
  selfAssessedLevelAdaptability?: number;
  selfAssessedLevelDynamics?: number;
  selfAssessedLevelIndependence?: number;
  selfAssessedLevelLivePerformance?: number;
  selfAssessedLevelReadingMusic?: number;
  createDate?: Moment;
  modifyDate?: Moment;
  drummerId?: number;
}

export const defaultValue: Readonly<IDrummerStatistics> = {};
