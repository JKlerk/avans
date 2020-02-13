<template>
	<div>
		<CreateArtist ref="createartist" />
		<EditArtist ref="editartist" />
		<div class="flex justify-center pt-5 pb-10">
			<div class="mx-20 w-2/3">
				<!-- Header -->
				<div class="flex">
					<h1 class="text-3xl font-bold">Artists</h1>
					<div class="flex flex-1 justify-end">
						<div class="w-1/2 text-center bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm mx-2 flex">
							<div class="my-auto mr-3">
								<search-icon size="1.0x" />
							</div>
							<input placeholder="Search for artists" class="appearance-none focus:outline-none w-full">
						</div>
					</div>
				</div>
				<!-- Body -->
				<div class="flex flex-wrap mt-10">
					<div class="flex shadow rounded-lg bg-white w-1/2 hover:opacity-50 transition duration-200 cursor-pointer mb-5" :key="artist.id" v-for="artist in this.$store.state.artists" @click="$refs.editartist.visible = true, $refs.editartist.artist = artist">
						<div class="rounded-l-lg w-1/3" style="background-image: url('https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60'); background-position: center; background-size: cover;"></div>
						<div class="py-10 w-2/3">
							<h2 class="text-2xl ml-4 text-purple-700">{{ artist.name }}</h2>
							<p class="text-gray-700 ml-4">{{ artist.desc }}</p>
						</div>
					</div>
					<div class="bg-white w-1/2 rounded-lg shadow h-auto flex items-center justify-center hover:bg-gray-100 cursor-pointer" @click="$refs.createartist.visible = true" >
						<p class="text-6xl text-purple-600">+</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import { SearchIcon } from 'vue-feather-icons'
import CreateArtist from '@/components/Artist/CreateArtist'
import EditArtist from '@/components/Artist/EditArtist'
import api from '@/api/artists.js'

export default {
	components:{
		SearchIcon,
		CreateArtist,
		EditArtist
	},
	created(){
		if(Object.keys(this.$store.state.artists).length === 0){
			api.getArtists().then((response) => {
				this.$store.state.artists = response.data
			})
		}
	},
}
</script>