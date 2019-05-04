import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFinishedExcercise, defaultValue } from 'app/shared/model/finished-excercise.model';

export const ACTION_TYPES = {
  FETCH_FINISHEDEXCERCISE_LIST: 'finishedExcercise/FETCH_FINISHEDEXCERCISE_LIST',
  FETCH_FINISHEDEXCERCISE: 'finishedExcercise/FETCH_FINISHEDEXCERCISE',
  CREATE_FINISHEDEXCERCISE: 'finishedExcercise/CREATE_FINISHEDEXCERCISE',
  UPDATE_FINISHEDEXCERCISE: 'finishedExcercise/UPDATE_FINISHEDEXCERCISE',
  DELETE_FINISHEDEXCERCISE: 'finishedExcercise/DELETE_FINISHEDEXCERCISE',
  RESET: 'finishedExcercise/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFinishedExcercise>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FinishedExcerciseState = Readonly<typeof initialState>;

// Reducer

export default (state: FinishedExcerciseState = initialState, action): FinishedExcerciseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FINISHEDEXCERCISE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FINISHEDEXCERCISE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FINISHEDEXCERCISE):
    case REQUEST(ACTION_TYPES.UPDATE_FINISHEDEXCERCISE):
    case REQUEST(ACTION_TYPES.DELETE_FINISHEDEXCERCISE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FINISHEDEXCERCISE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FINISHEDEXCERCISE):
    case FAILURE(ACTION_TYPES.CREATE_FINISHEDEXCERCISE):
    case FAILURE(ACTION_TYPES.UPDATE_FINISHEDEXCERCISE):
    case FAILURE(ACTION_TYPES.DELETE_FINISHEDEXCERCISE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FINISHEDEXCERCISE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FINISHEDEXCERCISE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FINISHEDEXCERCISE):
    case SUCCESS(ACTION_TYPES.UPDATE_FINISHEDEXCERCISE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FINISHEDEXCERCISE):
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

const apiUrl = 'api/finished-excercises';

// Actions

export const getEntities: ICrudGetAllAction<IFinishedExcercise> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FINISHEDEXCERCISE_LIST,
  payload: axios.get<IFinishedExcercise>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFinishedExcercise> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FINISHEDEXCERCISE,
    payload: axios.get<IFinishedExcercise>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFinishedExcercise> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FINISHEDEXCERCISE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFinishedExcercise> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FINISHEDEXCERCISE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFinishedExcercise> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FINISHEDEXCERCISE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
