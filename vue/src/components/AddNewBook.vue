<template>
  <form class="new-book-form" v-on:submit.prevent>
    <!-- <input class="new-book-input" type="text" placeholder="Book-ID" v-model="book.book_id" /> -->


    <label for="booktitle">Title: </label>
    
    <input class="new-book-input" type="text" placeholder="Title" id ="bookTitle" v-model="book.title" />
    <label for="bookAuthor">Author: </label>
    <input class="new-book-input" type="text" placeholder="Author" id ="bookAuthor" v-model="book.author" />
    <label for="bookIsbn">ISBN: </label>
    <input class="new-book-input" type="text" placeholder="ISBN" id ="bookIsbn" v-model="book.isbn" />
     <label for="description">Description: </label>
    <textarea class="new-book-input" id="description" placeholder="Description of Book"  v-model="book.description" />
    <br>
    <button v-on:click="postBook()" >Add Book to List</button>
    <button v-on:click="cancel()">Cancel </button>
  </form>
</template>

<script>
import bookService from '../services/BookService.js'
export default {
        name: "add-newbook-form",
    data() {
        return {
            book: {
                isbn: '',
                title: '',
                author: '',
                coverImage: '',
                description: ''
            }
        }
    },
        methods: {
        // saveBook() {
        //     if (this.book.title && this.book.author && this.book.isbn){
        //     this.$store.commit('SAVE_BOOK', this.book);
        //     this.book = {
        //         book_id: '',
        //         title: '',
        //         author: '',
        //         cover_image: '',
        //         description: '',
        //         isbn: ''
        //     };
        //     this.$router.push({name: 'books'});
        // } else {
        //     alert('Please fill in all fields');
        // }},
    postBook(){
    bookService.postBook(this.book)
    .then(response=>{
        if(response.status === 201){
          this.$router.push('/books')

        } 
        })
    },
    cancel(){
        this.$router.push('/')
    }
    
    
    }

}
</script>

<style scoped>

.new-book-input {
 width: 50%;
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

form {
  margin-bottom: 1rem;
  background-color: rgba(141, 141, 141, 0.5);
  padding: 1rem; 
  border-radius: 15px;
  display: grid;
  justify-items: center;
  width: 50vw;
  
  
}

button {
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

button:hover {
  background: linear-gradient(to right,#102770,#ad63f1 );
  color: #ffffff;
  box-shadow: 0 8px 24px 0 rgba(16,39,112,.2);
}


</style>