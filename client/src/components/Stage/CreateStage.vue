<template>
    <transition name="fade" v-if="visible">
        <div>
            <div class="bg-black opacity-75 fixed min-h-screen z-0 w-full top-0"></div>
            <div class="flex justify-center">
                <div class="z-40 bg-white w-1/3 fixed rounded-lg shadow overflow-auto top-0 mt-5" style="max-height: 600px">
                    <div class="shadow flex flex-col items-center justify-center p-10 rounded-t-lg select-none relative" style="height: 350px; background: linear-gradient(rgba(0, 0, 0, 0.85), rgba(54, 13, 123, 0.8)), url(https://images.unsplash.com/photo-1464375117522-1311d6a5b81f?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=2250&amp;q=80);background-repeat: no-repeat;background-position: center center;">
                        <h1 class="font-bold text-3xl text-white">{{ stage.name }}</h1>
                    </div>
                    <div class="p-6">
                        <div class="mx-6">
                            <div class="mb-6">
                                <div class="py-4 mt-5">
                                    <div class="mb-4">
                                        <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Stage name:</label>
                                        <input @click="deleteError('name')" v-model="stage.name" :class="{ 'border-red-500' : hasError('name'), 'border-purple-200' : !hasError('name') }" class="w-full block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                                        <p class="text-sm text-red-500" v-if="hasError('name')">{{ hasError('name').message }}</p>
                                    </div>
                                    <div class="mb-4">
                                        <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Stage Description:</label>
                                        <textarea @click="deleteError('description')" rows="5" :class="{ 'border-red-500' : hasError('description'), 'border-purple-200' : !hasError('description') }" v-model="stage.description" class="w-full block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-4 text-sm"></textarea>
                                        <p class="text-sm text-red-500" v-if="hasError('description')">{{ hasError('description').message }}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="flex mt-5 items-center justify-between bg-purple-100 border-t-2 border-purple-200 px-8 py-6 -m-6 rounded-b-lg">
                            <button @click="submit" class="text-white bg-purple-500 hover:bg-purple-600 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none">Add</button>
                            <button @click="visible = false, stage = { name: 'Please enter a name'}" class="text-purple-100 bg-purple-700 hover:bg-purple-800 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </transition>
</template>

<script>
import { Edit2Icon } from 'vue-feather-icons'
import api from '@/api/stages.js'
export default {
    data(){
        return{
            visible: false,
            errors: [],
            stage: {
                name: 'Please enter a name',
                description: '',
            },
        }
    },
    components:{
        Edit2Icon,
    },
    methods:{
        submit(){
            if(this.stage.name === ''){
                this.errors.push({
                    field: 'name',
                    message: 'The name field is required'
                })
            }
            if(this.stage.description === ''){
                this.errors.push({
                    field: 'description',
                    message: 'The description field is required'
                })
            }

            if(this.errors.length === 0){
                api.addStage(JSON.stringify(this.stage)).then((response) => {
                    if(response.data){
                        this.stage.id = response.data[0].GENERATED_KEY;
                        this.$store.state.stages.push(this.stage);
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
        }
    },
}
</script>