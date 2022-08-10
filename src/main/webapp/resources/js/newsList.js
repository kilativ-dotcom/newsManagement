function deleteSubmitted() {
    let deleteNewsIds = document.getElementsByName("deleteNewsId");
    let anyIsDeleted = false;
    for (const newsId of deleteNewsIds.values()) {
        if (newsId.checked) {
            anyIsDeleted = true;
            break;
        }
    }
    if (!anyIsDeleted) {
        alert("Please check at least one news to delete");
        return anyIsDeleted;
    } else {
        return confirm("Are you sure you want to delete selected news?")
    }
}