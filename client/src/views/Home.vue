<template>
	<div>
		<EditArtist :bands="bands" ref="editartist"/>
		<CreateArtist :bands="bands" ref="createartist"/>
		<DeleteArtist ref="deleteartist"/>
		<div class="mt-2 min-h-screen" id="gradient">
			<div class="flex justify-center pt-10">
				<div class="w-1/3 shadow mx-5 px-4">
					<h1 class="py-4 text-2xl">Artists</h1>
					<div :key="artist.id" v-for="artist in artists">
						<Artist :artist=artist />
					</div>
					<div class="flex">
						<button @click="$refs.createartist.visible = true" class="px-2 py-1 text-white bg-green-500 hover:bg-green-600 rounded w-1/3 my-4 mr-2 transition duration-100 focus:outline-none">Create new artist</button>
					</div>
				</div>
				<div class="w-1/3 shadow mx-5 px-4">
					<h1 class="py-4 text-2xl">Stages</h1>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import Artist from '@/components/Artist'
import EditArtist from '@/components/EditArtist'
import CreateArtist from '@/components/CreateArtist'
import DeleteArtist from '@/components/DeleteArtist'
export default {
	components:{
		Artist,
		EditArtist,
		CreateArtist,
		DeleteArtist
	},
	data(){
		return{
			artists:[
				{
					id: 1,
					name: 'Jan',
					band: {
						id: 1,
						name: 'Band 1'
					}
				},
				{
					id: 2,
					name: 'Kees',
					band: {
						id: 2,
						name: 'Band 2'
					}
				}
			],
			bands:[
				{
					id: 1,
					name: 'Band 1'
				},
				{
					id: 2,
					name: 'Band 2'
				}
			]
		}
  	},
}
</script>

<style scoped>
	#gradient{
		background: #D3CCE3;
		/* background: -webkit-linear-gradient(-90deg, rgb(89, 0, 212), rgb(255, 94, 0)); */
		  background-attachment: fixed;
		background-position: center;
		background-repeat: no-repeat;
		background-size: cover;
		background: -webkit-linear-gradient(90deg, #E9E4F0, rgb(253, 253, 255));
		/* background: linear-gradient(45deg, #E9E4F0, #D3CCE3); */
	}
</style>
