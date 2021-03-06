package com.naturalautomation.selenium.pages;

import com.naturalautomation.exceptions.PageNotMappedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class PageFactory {

    private final Set<? extends Named> pages;

    @Autowired
    public <T extends Page & Named> PageFactory(Set<T> pages) {
        this.pages = pages;
    }

    public Page getPage(String name) throws PageNotMappedException {
        Optional<? extends Named> namedPage = pages.stream().filter(p -> p.getName().equals(name)).findFirst();

        return (Page) namedPage
                .orElseThrow(() ->
                        new PageNotMappedException(
                                String.format("Page %s is not mapped. Check if a page extending the " +
                                        "'Page' class and implementing the 'Named' interface exists.", name)));
    }

}
