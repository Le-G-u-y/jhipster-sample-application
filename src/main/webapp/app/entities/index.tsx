import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import I18nText from './i-18-n-text';
import Drummer from './drummer';
import Role from './role';
import Permission from './permission';
import Plan from './plan';
import ExcerciseConfig from './excercise-config';
import Excercise from './excercise';
import FinishedSession from './finished-session';
import FinishedExcercise from './finished-excercise';
import DrummerStatistics from './drummer-statistics';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/i-18-n-text`} component={I18nText} />
      <ErrorBoundaryRoute path={`${match.url}/drummer`} component={Drummer} />
      <ErrorBoundaryRoute path={`${match.url}/role`} component={Role} />
      <ErrorBoundaryRoute path={`${match.url}/permission`} component={Permission} />
      <ErrorBoundaryRoute path={`${match.url}/plan`} component={Plan} />
      <ErrorBoundaryRoute path={`${match.url}/excercise-config`} component={ExcerciseConfig} />
      <ErrorBoundaryRoute path={`${match.url}/excercise`} component={Excercise} />
      <ErrorBoundaryRoute path={`${match.url}/finished-session`} component={FinishedSession} />
      <ErrorBoundaryRoute path={`${match.url}/finished-excercise`} component={FinishedExcercise} />
      <ErrorBoundaryRoute path={`${match.url}/drummer-statistics`} component={DrummerStatistics} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
