import { createSlice, createAsyncThunk, PayloadAction } from '@reduxjs/toolkit';
import axios from 'axios';
import { Trainer } from '../types/models';


interface TrainersState {
    trainers: Trainer[];
    status: 'idle' | 'loading' | 'succeeded' | 'failed';
    error: string | null;
}

const initialState: TrainersState = {
    trainers: [],
    status: 'idle',
    error: null,
};

// Asynchroniczne pobieranie danych trenerów
export const fetchTrainers = createAsyncThunk('trainers/fetchTrainers', async () => {
    const response = await axios.get<Trainer[]>('http://localhost:7011/api/trainers');
    return response.data;
});

const trainersSlice = createSlice({
    name: 'trainers',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(fetchTrainers.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(fetchTrainers.fulfilled, (state, action: PayloadAction<Trainer[]>) => {
                state.status = 'succeeded';
                state.trainers = action.payload;
            })
            .addCase(fetchTrainers.rejected, (state, action) => {
                state.status = 'failed';
                state.error = action.error.message ?? null;
            });
    },
});

export default trainersSlice.reducer;
