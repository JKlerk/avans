<template>
    <div class="shadow rounded-lg my-4 select-none">
        <div class="flex w-full hover:bg-gray-200 p-4 transition duration-100" @click="visible = !visible">
            <p class="mx-2">{{ artist.id }}.</p>
            <p>{{ artist.name }}</p>
        </div>
        <transition name="fade">
            <div v-if="visible" class="p-4 border-t">
                <p>User ID: {{ artist.id }}</p>
                <p>Name: {{ artist.name }}</p>
                <p>Band: {{ artist.band.name }}</p>
                <button class="appereance-none bg-green-500 hover:bg-green-600 py-1 text-white rounded px-2 text-sm mt-2 transition duration-100 focus:outline-none" @click="editArtist(artist)">Edit Artist</button>
				<button class="appereance-none bg-red-500 hover:bg-red-600 py-1 text-white rounded px-2 text-sm mt-2 ml-4 transition duration-100 focus:outline-none" @click="deleteArtist(artist)">Delete artist</button>

            </div>
        </transition>
    </div>
</template>

<script>
export default {
    props: ['artist'],
    data(){
        return{
            visible: false
        }
    },
    methods:{
        editArtist(artist){
            this.$parent.$refs.editartist.artist = artist
            this.$parent.$refs.editartist.visible = true
        },
        deleteArtist(artist){
            this.$parent.$refs.deleteartist.artist = artist
            this.$parent.$refs.deleteartist.visible = true
        }
    }
}
</script>