import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFinishedSession, defaultValue } from 'app/shared/model/finished-session.model';

export const ACTION_TYPES = {
  FETCH_FINISHEDSESSION_LIST: 'finishedSession/FETCH_FINISHEDSESSION_LIST',
  FETCH_FINISHEDSESSION: 'finishedSession/FETCH_FINISHEDSESSION',
  CREATE_FINISHEDSESSION: 'finishedSession/CREATE_FINISHEDSESSION',
  UPDATE_FINISHEDSESSION: 'finishedSession/UPDATE_FINISHEDSESSION',
  DELETE_FINISHEDSESSION: 'finishedSession/DELETE_FINISHEDSESSION',
  RESET: 'finishedSession/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFinishedSession>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FinishedSessionState = Readonly<typeof initialState>;

// Reducer

export default (state: FinishedSessionState = initialState, action): FinishedSessionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FINISHEDSESSION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FINISHEDSESSION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FINISHEDSESSION):
    case REQUEST(ACTION_TYPES.UPDATE_FINISHEDSESSION):
    case REQUEST(ACTION_TYPES.DELETE_FINISHEDSESSION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FINISHEDSESSION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FINISHEDSESSION):
    case FAILURE(ACTION_TYPES.CREATE_FINISHEDSESSION):
    case FAILURE(ACTION_TYPES.UPDATE_FINISHEDSESSION):
    case FAILURE(ACTION_TYPES.DELETE_FINISHEDSESSION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FINISHEDSESSION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FINISHEDSESSION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FINISHEDSESSION):
    case SUCCESS(ACTION_TYPES.UPDATE_FINISHEDSESSION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FINISHEDSESSION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/finished-sessions';

// Actions

export const getEntities: ICrudGetAllAction<IFinishedSession> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FINISHEDSESSION_LIST,
  payload: axios.get<IFinishedSession>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFinishedSession> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FINISHEDSESSION,
    payload: axios.get<IFinishedSession>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFinishedSession> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FINISHEDSESSION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFinishedSession> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FINISHEDSESSION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFinishedSession> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FINISHEDSESSION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
