<template>
	<div>
		<!-- Artists -->
		<EditArtist ref="editartist"/>
		<CreateArtist ref="createartist"/>
		<DeleteArtist ref="deleteartist"/>
		<!-- Stages -->
		<EditStage ref="editstage"/>
		<CreateStage ref="createstage"/>
		<DeleteStage ref="deletestage"/>
		<div class="mt-2 min-h-screen" id="gradient">
			<div class="flex justify-center pt-10">
				<div class="w-1/3 shadow mx-5 px-4">
					<h1 class="py-4 text-2xl">Artists</h1>
					<div :key="artist.id" v-for="artist in artists">
						<Artist :artist=artist />
					</div>
					<button @click="$refs.createartist.visible = true" class="px-2 py-1 text-white bg-green-500 hover:bg-green-600 rounded w-1/3 my-4 mr-2 transition duration-100 focus:outline-none">Create new artist</button>
				</div>
				<div class="w-1/3 shadow mx-5 px-4">
					<h1 class="py-4 text-2xl">Stages</h1>
					<div :key="stage.id" v-for="stage in stages">
						<Stage :stage=stage />
					</div>
					<button @click="$refs.createstage.visible = true" class="px-2 py-1 text-white bg-green-500 hover:bg-green-600 rounded w-1/3 my-4 mr-2 transition duration-100 focus:outline-none">Create new stage</button>
				</div>
			</div>
			<div class="flex justify-center pt-10">
				<div class="w-1/3 shadow mx-5 px-4">
					<h1 class="py-4 text-2xl">Performances</h1>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
// Artists
import Artist from '@/components/Artist/Artist'
import EditArtist from '@/components/Artist/EditArtist'
import CreateArtist from '@/components/Artist/CreateArtist'
import DeleteArtist from '@/components/Artist/DeleteArtist'

// Stages
import Stage from '@/components/Stage/Stage'
import EditStage from '@/components/Stage/EditStage'
import CreateStage from '@/components/Stage/CreateStage'
import DeleteStage from '@/components/Stage/DeleteStage'
export default {
	components:{
		Artist,
		EditArtist,
		CreateArtist,
		DeleteArtist,
		Stage,
		EditStage,
		CreateStage,
		DeleteStage,
	},
	data(){
		return{
			artists:[
				{
					id: 1,
					name: 'Frans',
					desc: 'Amet quaerat porro aliquid tenetur reiciendis unde, repellendus voluptatem soluta animi accusamus neque voluptatibus laborum consectetur perspiciatis, sint nihil sequi est incidunt.'
				},
				{
					id: 2,
					name: 'Bauer',
					desc: 'Amet quaerat porro aliquid tenetur reiciendis unde, repellendus voluptatem soluta animi accusamus neque voluptatibus laborum consectetur perspiciatis, sint nihil sequi est incidunt.'
				}
			],
			stages:[
				{
					id: 1,
					name: 'Stage 1',
					desc: 'Amet quaerat porro aliquid tenetur reiciendis unde, repellendus voluptatem soluta animi accusamus neque voluptatibus laborum consectetur perspiciatis, sint nihil sequi est incidunt.'
				},
				{
					id: 2,
					name: 'Stage 2',
					desc: 'Amet quaerat porro aliquid tenetur reiciendis unde, repellendus voluptatem soluta animi accusamus neque voluptatibus laborum consectetur perspiciatis, sint nihil sequi est incidunt.'
				}
			],
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
