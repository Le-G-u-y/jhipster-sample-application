import { Moment } from 'moment';

export interface II18nText {
  id?: number;
  locale?: string;
  textKey?: string;
  textContent?: string;
  createDate?: Moment;
  modifyDate?: Moment;
}

export const defaultValue: Readonly<II18nText> = {};
