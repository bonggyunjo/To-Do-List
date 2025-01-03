    package com.example.TodoList.controller.records;


    import com.example.TodoList.dto.PageDto;
    import com.example.TodoList.entity.Board;
    import com.example.TodoList.entity.User;
    import com.example.TodoList.entity.record.Block;
    import com.example.TodoList.entity.record.Page;
    import com.example.TodoList.entity.record.ProgressStatus;
    import com.example.TodoList.repository.PageRepository;
    import com.example.TodoList.repository.UserRepository;
    import com.example.TodoList.service.PageService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Map;

    @RestController
    @Controller
    public class PageController {
        @Autowired
        private PageService pageService;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PageRepository pageRepository;
        @PostMapping("/pages/create")
        public ResponseEntity<Page> createPage(@RequestBody PageDto pagedto) {
            try {
                String userId = pagedto.getUserId();
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                Page newPage = pageService.createPage(pagedto.getTitle(), pagedto.getContent(), pagedto.getPriority(), pagedto.getUserId(), pagedto.getProgressStatus());
                return ResponseEntity.ok(newPage);
            } catch (Exception e) {

                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }


        @GetMapping("/pages/{userId}")
        public ResponseEntity<List<Page>> getPagesByUserId(@PathVariable String userId, @RequestParam(required = false) Boolean deleted) {
            List<Page> pages;
            if (deleted != null) {
                pages = pageRepository.findByUser_UserIdAndDeleted(userId, deleted);
            } else {
                pages = pageRepository.findByUser_UserId(userId);
            }
            return ResponseEntity.ok(pages);
        }
        @PutMapping("/pages/{id}")
        public ResponseEntity<Page> updatePage(@PathVariable Long id, @RequestBody Page updatedPage) {
            Page updatepage = pageService.updatePage(id, updatedPage.getTitle(), updatedPage.getContent());
            return ResponseEntity.ok(updatepage);
        }


        @PatchMapping("/pages/{id}/delete")
        public ResponseEntity<Page> moveToTrash(@PathVariable Long id) {
            Page page = pageService.moveToTrash(id);
            return  ResponseEntity.ok(page);
        }


        @GetMapping("/pages/deleted")
        public ResponseEntity<List<Page>> getDeletedPages(){
            List<Page> deletedPages = pageService.getDeletedPage();
            return ResponseEntity.ok(deletedPages);
        }

        @PatchMapping("/pages/{id}/restore")
        public ResponseEntity<Page> restorePage(@PathVariable Long id) {
            Page restoredPage = pageService.restorePage(id);
            return ResponseEntity.ok(restoredPage);
        }

        @DeleteMapping("/pages/{id}/permanent")
        public ResponseEntity<Void> permanentDeletePage(@PathVariable Long id) {
            pageService.permanentDeletePage(id);
            return ResponseEntity.noContent().build();
        }

        @PatchMapping("/pages/{id}/priority")
        public ResponseEntity<Page> updatePriority(@PathVariable Long id, @RequestBody Map<String, Integer> payload) {
            Integer priority = payload.get("priority");
            if (priority == null) {
                return ResponseEntity.badRequest().body(null); 
            }
            Page updatedPage = pageService.updatePriority(id, priority);
            return ResponseEntity.ok(updatedPage);
        }


        @GetMapping("/pages/sorted/{userId}")
        public ResponseEntity<List<Page>> getPagesByUserIdSorted(@PathVariable String userId) {
            List<Page> pages = pageService.findByUserIdSortedByPriority(userId);
            return ResponseEntity.ok(pages);
        }

        @PatchMapping("/pages/{id}/progress")
        public ResponseEntity<Page> updateProgressStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
            String statusString = payload.get("status");
            if (statusString == null) {
                return ResponseEntity.badRequest().body(null);
            }

            ProgressStatus progressStatus;
            try {
                progressStatus = ProgressStatus.valueOf(statusString.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(null);
            }

            Page updatedPage = pageService.updateProgressStatus(id, progressStatus);
            return ResponseEntity.ok(updatedPage);
        }
    }

