import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import i18nText, {
  I18nTextState
} from 'app/entities/i-18-n-text/i-18-n-text.reducer';
// prettier-ignore
import drummer, {
  DrummerState
} from 'app/entities/drummer/drummer.reducer';
// prettier-ignore
import role, {
  RoleState
} from 'app/entities/role/role.reducer';
// prettier-ignore
import permission, {
  PermissionState
} from 'app/entities/permission/permission.reducer';
// prettier-ignore
import plan, {
  PlanState
} from 'app/entities/plan/plan.reducer';
// prettier-ignore
import excerciseConfig, {
  ExcerciseConfigState
} from 'app/entities/excercise-config/excercise-config.reducer';
// prettier-ignore
import excercise, {
  ExcerciseState
} from 'app/entities/excercise/excercise.reducer';
// prettier-ignore
import finishedSession, {
  FinishedSessionState
} from 'app/entities/finished-session/finished-session.reducer';
// prettier-ignore
import finishedExcercise, {
  FinishedExcerciseState
} from 'app/entities/finished-excercise/finished-excercise.reducer';
// prettier-ignore
import drummerStatistics, {
  DrummerStatisticsState
} from 'app/entities/drummer-statistics/drummer-statistics.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly i18nText: I18nTextState;
  readonly drummer: DrummerState;
  readonly role: RoleState;
  readonly permission: PermissionState;
  readonly plan: PlanState;
  readonly excerciseConfig: ExcerciseConfigState;
  readonly excercise: ExcerciseState;
  readonly finishedSession: FinishedSessionState;
  readonly finishedExcercise: FinishedExcerciseState;
  readonly drummerStatistics: DrummerStatisticsState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  i18nText,
  drummer,
  role,
  permission,
  plan,
  excerciseConfig,
  excercise,
  finishedSession,
  finishedExcercise,
  drummerStatistics,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
