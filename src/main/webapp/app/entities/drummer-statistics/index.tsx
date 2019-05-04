import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DrummerStatistics from './drummer-statistics';
import DrummerStatisticsDetail from './drummer-statistics-detail';
import DrummerStatisticsUpdate from './drummer-statistics-update';
import DrummerStatisticsDeleteDialog from './drummer-statistics-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DrummerStatisticsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DrummerStatisticsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DrummerStatisticsDetail} />
      <ErrorBoundaryRoute path={match.url} component={DrummerStatistics} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DrummerStatisticsDeleteDialog} />
  </>
);

export default Routes;
