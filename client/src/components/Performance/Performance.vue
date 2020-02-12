<template>
    <transition name="fade" v-if="visible">
        <div>
            <div class="bg-black opacity-75 fixed min-h-screen z-0 w-full top-0"></div>
            <div class="flex justify-center">
                <div class="z-40 bg-white w-1/3 fixed rounded-lg shadow overflow-auto mt-5" style="max-height: 600px">
                    <div class="shadow flex flex-col items-center justify-center p-10 rounded-t-lg relative select-none" style="height: 350px; background: linear-gradient(rgba(0, 0, 0, 0.85), rgba(54, 13, 123, 0.8)), url(https://images.unsplash.com/photo-1464375117522-1311d6a5b81f?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=2250&amp;q=80);background-repeat: no-repeat;background-position: center center;">
                        <div class="top-0 absolute left-0 text-white m-4 bg-purple-900 p-2 hover:bg-purple-800 cursor-pointer transition duration-100 rounded-full" @click="isEditing = !isEditing">
                            <edit-2-icon class="" size="1.0x"></edit-2-icon>
                        </div>
                        <h1 class="font-bold text-3xl text-white">{{ performance.name }}</h1>
                        <h2 class="font-bold font-medium mt-2 text-sm text-white tracking-widest uppercase">{{ artist.name }}</h2>
                        <h3 class="bg-white font-bold font-semibold inline-block leading-6 mt-8 px-4 py-1 rounded-full text-purple-500 text-xs tracking-wider uppercase">{{ stage.name }}</h3>
                        <button v-if="isEditing == false" @click="visible = false" class="text-purple-100 bg-purple-700 hover:bg-purple-800 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none absolute bottom-0 right-0 m-3">Close</button>
                        <span class="bg-purple-700 font-bold font-semibold inline-block leading-6 mt-8 px-4 py-1 rounded-full text-white text-xs tracking-wider uppercase bottom-0 left-0 absolute m-3">{{ performance.time }}</span>
                    </div>
                    <div v-if="isEditing" class="p-6">
                        <div class="mx-6">
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Performance name:</label>
                                <input v-model="performance.name" autofocus class="w-full block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                            </div>
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Artist:</label>
                                <select v-model="performance.artist_id" class="w-full bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm">
                                    <option :value="artist.id" :key="artist.id" v-for="artist in $parent.getData().artists">{{ artist.name }}</option>
                                </select>
                            </div>
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Stage:</label>
                                <select v-model="performance.stage_id" class="w-full bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm">
                                    <option class="py-2" :value="stage.id" :key="stage.id" v-for="stage in $parent.getData().stages">{{ stage.name }}</option>
                                </select>
                            </div>
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Time/Date:</label>
                                <input type="datetime-local" class="w-full block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-3 text-sm" id="birthdaytime" name="birthdaytime">
                            </div>
                            <div class="mb-2">
                                <p class="font-medium text-sm tracking-wide text-purple-800 hover:underline cursor-pointer select-none" @click="confirm = true">Delete performance</p>
                            </div>
                            <div v-if="confirm" class="mb-2">
                                <p class="text-sm">Are you sure?</p>
                                <div class="flex mt-2">
                                    <button class="mr-2 text-sm p-1 bg-purple-700 text-white rounded-lg px-4 focus:outline-none">Yes</button>
                                    <button class="mx-2 text-sm p-1 bg-purple-700 text-white rounded-lg px-4 focus:outline-none" @click="confirm = false">No</button>
                                </div>
                            </div>
                        </div>
                        <div class="flex mt-5 items-center justify-between bg-purple-100 border-t-2 border-purple-200 px-8 py-6 -m-6 rounded-b-lg">
                            <button class="text-white bg-purple-500 hover:bg-purple-600 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none">Save</button>
                            <button @click="visible = false, isEditing = false" class="text-purple-100 bg-purple-700 hover:bg-purple-800 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </transition>
</template>

<script>
import { Edit2Icon } from 'vue-feather-icons'
export default {
    data(){
        return{
            isEditing: false,
            confirm: false,
            visible: false,
            artist: {},
            performance: {},
            stage: {},
        }
    },
    components:{
        Edit2Icon,
    },
}
</script>