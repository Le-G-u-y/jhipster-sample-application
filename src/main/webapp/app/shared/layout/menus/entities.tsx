import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <MenuItem icon="asterisk" to="/entity/i-18-n-text">
      <Translate contentKey="global.menu.entities.i18NText" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/drummer">
      <Translate contentKey="global.menu.entities.drummer" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/role">
      <Translate contentKey="global.menu.entities.role" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/permission">
      <Translate contentKey="global.menu.entities.permission" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/plan">
      <Translate contentKey="global.menu.entities.plan" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/excercise-config">
      <Translate contentKey="global.menu.entities.excerciseConfig" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/excercise">
      <Translate contentKey="global.menu.entities.excercise" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/finished-session">
      <Translate contentKey="global.menu.entities.finishedSession" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/finished-excercise">
      <Translate contentKey="global.menu.entities.finishedExcercise" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/drummer-statistics">
      <Translate contentKey="global.menu.entities.drummerStatistics" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
