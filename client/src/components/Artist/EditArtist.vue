<template>
    <transition name="fade" v-if="visible">
        <div>
            <div class="bg-black opacity-75 fixed min-h-screen z-0 w-full top-0"></div>
            <div class="flex justify-center">
                <div class="z-40 bg-white w-1/3 fixed rounded-lg shadow overflow-auto top-0 mt-5" style="max-height: 600px">
                    <div class="shadow flex flex-col items-center justify-center p-10 rounded-t-lg select-none relative" style="height: 350px; background: linear-gradient(rgba(0, 0, 0, 0.85), rgba(54, 13, 123, 0.8)), url(https://images.unsplash.com/photo-1464375117522-1311d6a5b81f?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=2250&amp;q=80);background-repeat: no-repeat;background-position: center center;">
                        <h1 class="font-bold text-3xl text-white">{{ artist.name }}</h1>
                    </div>
                    <div class="p-6">
                        <div class="mx-6 mb-2 py-4 mt-5">
                            <div class="mb-4">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Artist name:</label>
                                <input @click="deleteError('name')" v-model="artist.name" :class="{ 'border-red-500' : hasError('name'), 'border-purple-200' : !hasError('name') }" class="w-full block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-3 text-sm">
                                 <p class="text-sm text-red-500" v-if="hasError('name')">{{ hasError('name').message }}</p>
                            </div>
                            <div class="mb-8">
                                <label class="mr-2 font-medium text-sm tracking-wide text-purple-800 mt-4">Artist Description:</label>
                                <textarea @click="deleteError('description')" rows="5" style="min-height: 20px;" v-model="artist.description" :class="{ 'border-red-500' : hasError('description'), 'border-purple-200' : !hasError('description') }" class="w-full block mt-2 rounded-lg border focus:outline-none appearance-none px-2 py-2 leading-4 text-sm"></textarea>
                                <p class="text-sm text-red-500" v-if="hasError('description')">{{ hasError('description').message }}</p>
                            </div>
                            <div>
                                <button @click="remove" class="bg-red-500 hover:bg-red-600 duration-100 transition text-white py-2 rounded shadow text-sm px-5">Delete Artist</button>
                            </div>
                        </div>
                        <div class="flex mt-1 items-center justify-between bg-purple-100 border-t-2 border-purple-200 px-8 py-6 -m-6 rounded-b-lg">
                            <button @click="submit" class="text-white bg-purple-500 hover:bg-purple-600 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none">Save</button>
                            <button @click="visible = false, artist = {}" class="text-purple-100 bg-purple-700 hover:bg-purple-800 rounded-full px-8 py-2 text-sm font-semibold transition duration-100 focus:outline-none">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </transition>
</template>
<script>
import api from '@/api/artists'
export default {
    data(){
        return{
            visible: false,
            artist:{},
            errors: []
        }
    },
    methods:{
        submit(){   
            if(this.artist.name === ''){
                this.errors.push({
                    field: 'name',
                    message: 'The name field is required'
                })
            }
            if(this.artist.description === ''){
                this.errors.push({
                    field: 'description',
                    message: 'The description field is required'
                })
            }  

            if(this.errors.length === 0){
                api.editArtist(JSON.stringify(this.artist)).then((response) => {
                    if(response.data = "Success"){
                        this.visible = false;
                    }
                })   
            }
        },
        remove(){
            api.deleteArtist(JSON.stringify(this.artist)).then((response) => {
				if(response.data = "Success"){
                    this.$store.state.artists = this.$store.state.artists.filter(oldArtist => {
                        return !(oldArtist.id === this.artist.id)
                    });
                    this.visible = false;
                }
			})   
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
    }
}
</script>