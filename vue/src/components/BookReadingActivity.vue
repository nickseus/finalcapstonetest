<template>
  <div class ="readingactivity">
      
     <p>Pages Read: {{reading_activity.bookmarkPage}} Pages Read</p>
     <p>Time Spent Reading: {{reading_activity.readingTime}} Minutes</p>
     <p>Completed: {{reading_activity.completed}} </p>
      <p>Favorite: {{reading_activity.favorite}}</p>
     <!-- <p> userid: {{reading_activity.userId}}</p>
      <p> userid: {{$store.state.user.id}}</p>
      <p>userid again {{userIdAgain}}</p> -->
     <!-- {{book.is_completed ? 'You completed this book!' : 'You have not completed this book yet. Keep reading!'}} -->


        <div>
            <br>
        <!-- <button v-if="updateButton" v-on:click.prevent="updateButton = false">Update Book Activity</button> -->
        <button id="update-book-activity-btn" v-if="updateButton" v-on:click.prevent="updateButton = false">Update Book Activity</button>
        <form v-else v-on:submit.prevent="updatePutBookActivity">
             <label for="pagesRead"> Pages Read Today: </label>
          <input class ="new-book-input" type="text" placeholder="Pages Read" key="pages-read" v-model="reading_activity.bookmarkPage"/>

          <label for="minutesRead"> Minutes Read Today: </label>
          <input class ="new-book-input" type="text" placeholder="Minutes Read" key="minutes-read" v-model="reading_activity.readingTime"/>

          <label for="bookNotes"> Notes: </label>
          <input class ="new-book-input" type="text" placeholder="Notes" key="notes" v-model="reading_activity.notes"/>

          <label>Completed: </label>
          <input type="checkbox" placeholder="completed" v-model="reading_activity.completed"/>

          <label>Is Favorited: </label>
          <input type="checkbox" placeholder="is Favorited" v-model="reading_activity.favorite"/>
          <input id = "submit_reading_activity_btn" type="submit" value="Submit"/>
        </form>
        </div>
      
      </div>
</template>

<script>
import bookService from '../services/BookService.js'
export default {
    name: 'book-reading-activity',
    data(){
        return {
            updateButton: true,
             userIdAgain: this.$store.state.user.id,
            reading_activity: {
                bookId: this.$route.params.book_id,
              //  userId: this.$store.state.user.id,
                completed: '',
                favorite: '',
                bookmarkPage: '',
                readingTime: '',
                notes: ''
            }

        }
    },
    methods: {
            // updateBookActivity(){

            //    this.$store.commit("UPDATE_ACTIVITY", this.reading_activity)
            //    this.reading_activity = {
            //     book_id: this.$route.params.book_id,
            //     is_completed: false,
            //     is_favorite: false,
            //     bookmark_page_number: '',
            //     reading_time: '',
            //     notes: ''
            // }
            //    this.updateButton = true;
            //     //this.$router.push({name: 'books'});
            //    // console.log( this.newactivity.reading_time, this.newactivity.bookmark_page_number)

            // },
             updatePutBookActivity(){
                bookService.updateReadingActivity(this.reading_activity)
                .then(response=>{
                if(response.status === 200){
                    this.$router.push('/')
                }
                })
                .catch(error=>{
                 this.handleResponse(error, "updating")
                })
            }
    },
           
    created(){
          bookService.getReadingActivity(this.$route.params.book_id)
          .then((response) => {
      this.reading_activity = response.data;
    })
      }
    // computed:{},
        //  book(){
        // return this.$store.state.readingactivity.find(act=>act.book_id == this.$route.params.book_id)
     // }
    //   reading_activity(){
    //       bookService.getReadingActivity(this.$route.params.book_id)
    //       .then((response) => {
    //         this.reading_activity = response.data;
    //         });
      
      
      
    }


</script>

<style scoped>
.readingactivity{
  

  text-align: center;
  margin-bottom: 1rem;
  background-color: rgba(230, 230, 230, 0.5);
  padding: 1rem; 
  border-radius: 15px;
  display: grid;
  justify-items: center;
  
}

  

button,#update-book-activity-btn, #submit_reading_activity_btn {
    width: 220px;
    background: linear-gradient(to right,#00afef,#ad63f1 );
    color: white;
    border-radius: 15px;
    border: none;
    outline: none;
    padding: 23px 0;
    margin-top: 10px;
    cursor: pointer;
    margin-left: 10px;
}
button:hover,#update-book-activity-btn:hover,#submit_reading_activity_btn:hover {
  background: linear-gradient(to right,#102770,#ad63f1 );
  color: #ffffff;
  box-shadow: 0 8px 24px 0 rgba(16,39,112,.2);
}
label {
  display: block;
  margin-bottom: 5px;
}

input {
  display: block;
  margin-bottom: 10px;
}
.new-book-input{
   width: 90%;
    height: 40px;
    border: 1px solid white;
    background: rgb(226, 226, 226);
    padding: 0 20px;
    border-radius: 15px;
    outline: none;
    font-weight: 400;
    font-size: 14px;
    transition: all 0.5s ease;
    margin-top: 5px;
}
</style>