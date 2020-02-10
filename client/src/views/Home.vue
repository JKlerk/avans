<template>
	<div class="mt-2 min-h-screen" id="gradient">
		<div class="flex justify-center pt-10">
			<div class="w-1/3 shadow mx-5 px-4">
				<h1 class="py-4 text-2xl">Artists</h1>
				<div :key="artist.id" v-for="artist in artists">
					<Artist :artist=artist />
				</div>
			</div>
			<div class="w-1/3 shadow mx-5">
				<h1 class="p-4">Stages</h1>
			</div>
		</div>
	</div>
</template>

<script>
import Artist from '@/components/Artist'
export default {
  name: 'Home',
  components:{
	  Artist
  },
  data(){
	  return{
		  artists:[
			  {
				  id: 1,
				  name: 'Jan',
				  band_id: 1
			  },
			  {
				  id: 2,
				  name: 'Kees',
				  band_id: 1
			  }
		  ]
	  }
  }
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
