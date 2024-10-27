import { configureStore } from '@reduxjs/toolkit';
import trainersReducer from './trainersSlice';

export const store = configureStore({
  reducer: {
    trainers: trainersReducer,
  },
});

// Typy dla store i dispatch
export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;

