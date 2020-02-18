<template>
    <transition name="fade" v-if="visible">
        <div>
            <div class="bg-black opacity-75 fixed min-h-screen z-0 w-full top-0"></div>
            <div class="flex justify-center">
                <div class="z-40 bg-white w-1/3 fixed rounded-lg shadow overflow-auto top-0 mt-5" style="max-height: 600px">
                    <div class="shadow flex flex-col items-center justify-center p-10 rounded-t-lg select-none relative" style="height: 350px; background: linear-gradient(rgba(0, 0, 0, 0.85), rgba(54, 13, 123, 0.8)), url(https://images.unsplash.com/photo-1464375117522-1311d6a5b81f?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=2250&amp;q=80);background-repeat: no-repeat;background-position: center center;">
                        <h1 class="font-bold text-3xl text-white">{{ performance.name }}</h1>
                        <h2 class="font-bold font-medium mt-2 text-sm text-white tracking-widest uppercase">{{ $parent.getSpecificArtist(performance) ? $parent.getSpecificArtist(performance).name : '' }}</h2>
                        <h3 class="bg-white font-bold font-semibold inline-block leading-6 mt-8 px-4 py-1 rounded-full text-purple-500 text-xs tracking-wider uppercase">{{ $parent.getSpecificStage(performance) ? $parent.getSpecificStage(performance).name : '' }}</h3>
                        <span class="bg-purple-700 font-bold font-semibold inline-block leading-6 mt-8 px-4 py-1 rounded-full text-white text-xs tracking-wider uppercase bottom-0 left-0 absolute m-3">{{ getTime }}</span>
                    </div>
                    <div class="p-6">
                        <div class="mx-6">
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Performance name:</label>
                                <input @click="deleteError('name')" v-model="performance.name" autofocus :class="{ 'border-red-500' : hasError('name'), 'border-purple-200' : !hasError('name') }" class="w-full block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                                <p class="text-sm text-red-500" v-if="hasError('name')">{{ hasError('name').message }}</p>
                            </div>
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Artist:</label>
                                <select @click="deleteError('artist_id')" v-model="performance.artist_id" :class="{ 'border-red-500' : hasError('artist_id'), 'border-purple-200' : !hasError('artist_id') }" class="w-full bg-white block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-2 text-sm">
                                    <option selected disabled>Please select an artist</option>
                                    <option :value="artist.id" :key="artist.id" v-for="artist in $parent.artists">{{ artist.name }}</option>
                                </select>
                                <p class="text-sm text-red-500" v-if="hasError('artist_id')">{{ hasError('artist_id').message }}</p>
                                <p v-if="!addArtist.toggle" class="mr-2 font-medium text-sm tracking-wide text-purple-700 hover:underline mt-1 cursor-pointer select-none" @click="addArtist.toggle = true">+ Add new artist</p>
                                <div v-if="addArtist.toggle" class="pl-5 border-t border-b py-4 mt-5">
                                    <div class="mb-4">
                                        <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Artist name:</label>
                                        <input @click="deleteErrorArtist('name')" v-model="addArtist.artist.name" :class="{ 'border-red-500' : hasErrorArtist('name'), 'border-purple-200' : !hasErrorArtist('name') }" class="w-full block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                                        <p class="text-sm text-red-500" v-if="hasErrorArtist('name')">{{ hasErrorArtist('name').message }}</p>
                                    </div>
                                    <div class="mb-4">
                                        <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Artist Description:</label>
                                        <textarea @click="deleteErrorArtist('description')" v-model="addArtist.artist.description" :class="{ 'border-red-500' : hasErrorArtist('description'), 'border-purple-200' : !hasErrorArtist('description') }" class="w-full block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-3 text-sm"></textarea>
                                        <p class="text-sm text-red-500" v-if="hasErrorArtist('description')">{{ hasErrorArtist('description').message }}</p>
                                    </div>
                                    <p v-if="addArtist.success" class="text-green-500 text-sm">{{ addArtist.message }}</p> 
                                    <div class="flex justify-between">
                                        <button @click="createArtist" class="text-sm p-2 bg-green-500 hover:bg-green-600 text-white my-2 rounded-lg px-5 transition duration-100">Save</button>
                                        <button class="text-sm p-2 bg-red-500 hover:bg-red-600 text-white my-2 rounded-lg px-5 transition duration-100" @click="addArtist.toggle = false, addArtist.success = false, addAritst.mssage = '', addArtist.artist = {}">Close</button>
                                    </div>
                                </div>
                            </div>
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Stage:</label>
                                <select @click="deleteError('stage_id')" v-model="performance.stage_id" :class="{ 'border-red-500' : hasError('stage_id'), 'border-purple-200' : !hasError('stage_id') }" class="w-full bg-white block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-2 text-sm">
                                    <option class="py-2" :value="stage.id" :key="stage.id" v-for="stage in $parent.stages">{{ stage.name }}</option>
                                </select>
                                <p class="text-sm text-red-500" v-if="hasError('stage_id')">{{ hasError('stage_id').message }}</p>
                                <p v-if="!addStage.toggle"  class="mr-2 font-medium text-sm tracking-wide text-purple-700 hover:underline mt-1 cursor-pointer select-none" @click="addStage.toggle = true">+ Add new Stage</p>
                                <div v-if="addStage.toggle" class="pl-5 border-t border-b py-4 mt-5">
                                    <div class="mb-4">
                                        <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Stage name:</label>
                                        <input @click="deleteErrorStage('name')" v-model="addStage.stage.name" :class="{ 'border-red-500' : hasErrorStage('name'), 'border-purple-200' : !hasErrorStage('name') }" class="w-full block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                                        <p class="text-sm text-red-500" v-if="hasErrorStage('name')">{{ hasErrorStage('name').message }}</p>
                                    </div>
                                    <div class="mb-4">
                                        <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Stage Description:</label>
                                        <textarea @click="deleteErrorStage('description')" v-model="addStage.stage.description" :class="{ 'border-red-500' : hasErrorStage('description'), 'border-purple-200' : !hasErrorStage('description') }" class="w-full block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-3 text-sm"></textarea>
                                        <p class="text-sm text-red-500" v-if="hasErrorStage('description')">{{ hasErrorStage('description').message }}</p>
                                    </div>
                                    <p v-if="addStage.success" class="text-green-500 text-sm">{{ addStage.message }}</p>
                                    <div class="flex justify-between">
                                        <button @click="createStage" class="text-sm p-2 bg-green-500 hover:bg-green-600 text-white my-2 rounded-lg px-5 transition duration-100">Save</button>
                                        <button class="text-sm p-2 bg-red-500 hover:bg-red-600 text-white my-2 rounded-lg px-5 transition duration-100" @click="addStage.toggle = false, addStage.success = false, addStage.message = '', addStage.stage = {}">Close</button>
                                    </div>
                                </div>
                            </div>
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Time/Date:</label>
                                <input @click="deleteError('date')" v-model="date" type="datetime-local" :class="{ 'border-red-500' : hasError('date'), 'border-purple-200' : !hasError('date') }" class="w-full block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                                <p class="text-sm text-red-500" v-if="hasError('date')">{{ hasError('date').message }}</p>
                            </div>
                        </div>
                        <div class="flex mt-5 items-center justify-between bg-purple-100 border-t-2 border-purple-200 px-8 py-6 -m-6 rounded-b-lg">
                            <button @click="createPerformance" class="text-white bg-purple-500 hover:bg-purple-600 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none">Save</button>
                            <button @click="visible = false, performance = { name: 'Enter performance name'}, date = '', addArtist.toggle = false, addStage.toggle = false" class="text-purple-100 bg-purple-700 hover:bg-purple-800 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </transition>
</template>
<script>
import artistAPI from '@/api/artists'
import stageAPI from '@/api/stages'
import performanceAPI from '@/api/performances'
export default {
    data(){
        return{
            errors: [],
            addArtist:{
                errors: [],
                success: false,
                message: '',
                toggle: false,
                artist: {
                    name: '',
                    description: ''
                },
            },
            addStage:{
                errors: [],
                success: false,
                message: '',
                toggle: false,
                stage: {
                    name: '',
                    description: ''
                },
            },
            visible: false,
            performance:{
                name: 'Enter performance name',
                artist_id: '',
                stage_id: '',
                time: '',
                date: '',
            },
            date: '',
        }
    },

    methods:{
        createArtist(){
            if(this.addArtist.artist.name === ''){
                this.addArtist.errors.push({
                    field: 'name',
                    message: 'The name field is required'
                })
            }
            if(this.addArtist.artist.description === ''){
                this.addArtist.errors.push({
                    field: 'description',
                    message: 'The description field is required'
                })
            }

            if(this.addArtist.errors.length === 0){
                artistAPI.addArtist(JSON.stringify(this.addArtist.artist)).then((response) => {
                    if(response.data){
                        this.addArtist.artist.id = response.data[0].GENERATED_KEY;
                        this.$parent.artists.push(this.addArtist.artist);
                        this.addArtist.message = 'Artist has been added';
                        this.addArtist.success = true;
                    }
                })
            }
        },
        createStage(){
            if(this.addStage.stage.name === ''){
                this.addStage.errors.push({
                    field: 'name',
                    message: 'The name field is required'
                })
            }
            if(this.addStage.stage.description === ''){
                this.addStage.errors.push({
                    field: 'description',
                    message: 'The description field is required'
                })
            }
            if(this.addStage.errors.length === 0){
                stageAPI.addStage(JSON.stringify(this.addStage.stage)).then((response) => {
                    if(response.data){
                        this.addStage.stage.id = response.data[0].GENERATED_KEY;
                        this.$parent.stages.push(this.addStage.stage);
                        this.addStage.message = 'Stage has been added';
                        this.addStage.success = true;
                    }
                })
            }
        },
        createPerformance(){
            if(this.performance.name === ''){
                this.errors.push({
                    field: 'name',
                    message: 'The name field is required'
                })
            }
            if(this.performance.artist_id === ''){
                this.errors.push({
                    field: 'artist_id',
                    message: 'The artist field is required'
                })
            }
            if(this.performance.stage_id === ''){
                this.errors.push({
                    field: 'stage_id',
                    message: 'The stage field is required'
                })
            }
            if(this.performance.date === ''){
                this.errors.push({
                    field: 'date',
                    message: 'The date field is required'
                })
            }
            
            if(this.errors.length === 0){
                performanceAPI.addPerformance(JSON.stringify(this.performance)).then((response) => {
                    if(response.data){
                        this.performance.id = response.data[0].GENERATED_KEY;
                        this.$parent.performances.push(this.performance);
                        this.addStage.message = 'Performance has been added';
                        this.addStage.success = true;
                        this.visible = false;
                    }
                })
            }
        },
        hasError(field){
            var error = this.errors.find(element => element.field === field)
            if(error !== undefined){
                return error
            }
        },

        deleteError(field){
            this.errors = this.errors.filter(e => {
                return e.field !== field
            })
        },

        hasErrorArtist(field){
            var error = this.addArtist.errors.find(element => element.field === field)
            if(error !== undefined){
                return error
            }
        },

        deleteErrorArtist(field){
            this.addArtist.errors = this.addArtist.errors.filter(e => {
                return e.field !== field
            })
        },

        hasErrorStage(field){
            var error = this.addStage.errors.find(element => element.field === field)
            if(error !== undefined){
                return error
            }
        },

        deleteErrorStage(field){
            this.addStage.errors = this.addStage.errors.filter(e => {
                return e.field !== field
            })
        }
    },

    computed:{
        getTime(){
            this.performance.date = this.date.split('T')[0]
            return this.performance.time = this.date.split('T')[1]
        },
    }
}
</script>