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
                                <input v-model="performance.name" autofocus class="w-full block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                            </div>
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Artist:</label>
                                <select v-model="performance.artist_id" class="w-full bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm">
                                    <option selected disabled>Please select an artist</option>
                                    <option :value="artist.id" :key="artist.id" v-for="artist in $parent.artists">{{ artist.name }}</option>
                                </select>
                                <p v-if="!addArtist.toggle" class="mr-2 font-medium text-sm tracking-wide text-purple-700 hover:underline mt-1 cursor-pointer select-none" @click="addArtist.toggle = true">+ Add new artist</p>
                                <div v-if="addArtist.toggle" class="pl-5 border-t border-b py-4 mt-5">
                                    <div class="mb-4">
                                        <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Artist name:</label>
                                        <input v-model="addArtist.artist.name" class="w-full block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                                    </div>
                                    <div class="mb-4">
                                        <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Artist Description:</label>
                                        <textarea v-model="addArtist.artist.desc" class="w-full block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-3 text-sm"></textarea>
                                    </div>
                                    <div class="flex justify-between mt-3">
                                        <button class="text-sm p-2 bg-green-500 hover:bg-green-600 text-white my-2 rounded-lg px-5 transition duration-100">Save</button>
                                        <button class="text-sm p-2 bg-red-500 hover:bg-red-600 text-white my-2 rounded-lg px-5 transition duration-100" @click="addArtist.toggle = false, addArtist.artist = {}">Cancel</button>
                                    </div>
                                </div>
                            </div>
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Stage:</label>
                                <select v-model="performance.stage_id" class="w-full bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm">
                                    <option class="py-2" :value="stage.id" :key="stage.id" v-for="stage in $parent.stages">{{ stage.name }}</option>
                                </select>
                                <p v-if="!addStage.toggle"  class="mr-2 font-medium text-sm tracking-wide text-purple-700 hover:underline mt-1 cursor-pointer select-none" @click="addStage.toggle = true">+ Add new Stage</p>
                                <div v-if="addStage.toggle" class="pl-5 border-t border-b py-4 mt-5">
                                    <div class="mb-4">
                                        <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Stage name:</label>
                                        <input v-model="addStage.stage.name" class="w-full block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                                    </div>
                                    <div class="mb-4">
                                        <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Stage Description:</label>
                                        <textarea v-model="addStage.stage.desc" class="w-full block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-3 text-sm"></textarea>
                                    </div>
                                    <div class="flex justify-between mt-3">
                                        <button class="text-sm p-2 bg-green-500 hover:bg-green-600 text-white my-2 rounded-lg px-5 transition duration-100">Save</button>
                                        <button class="text-sm p-2 bg-red-500 hover:bg-red-600 text-white my-2 rounded-lg px-5 transition duration-100" @click="addStage.toggle = false, addStage.stage = {}">Cancel</button>
                                    </div>
                                </div>
                            </div>
                            <div class="mb-6">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800">Time/Date:</label>
                                <input v-model="date" type="datetime-local" class="w-full block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                            </div>
                        </div>
                        <div class="flex mt-5 items-center justify-between bg-purple-100 border-t-2 border-purple-200 px-8 py-6 -m-6 rounded-b-lg">
                            <button class="text-white bg-purple-500 hover:bg-purple-600 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none">Save</button>
                            <button @click="visible = false, performance = { name: 'Enter performance name'}, date = '', addArtist.toggle = false, addStage.toggle = false" class="text-purple-100 bg-purple-700 hover:bg-purple-800 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </transition>
</template>
<script>
export default {
    data(){
        return{
            addArtist:{
                toggle: false,
                artist: {},
            },
            addStage:{
                toggle: false,
                stage: {},
            },
            visible: false,
            performance:{
                name: 'Enter performance name',
                time: '',
                date: '',
            },
            date: '',
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